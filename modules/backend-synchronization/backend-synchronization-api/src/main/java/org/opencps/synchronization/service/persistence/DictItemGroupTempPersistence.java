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

import org.opencps.synchronization.exception.NoSuchDictItemGroupTempException;
import org.opencps.synchronization.model.DictItemGroupTemp;

import java.util.Date;

/**
 * The persistence interface for the dict item group temp service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author trungdk
 * @see org.opencps.synchronization.service.persistence.impl.DictItemGroupTempPersistenceImpl
 * @see DictItemGroupTempUtil
 * @generated
 */
@ProviderType
public interface DictItemGroupTempPersistence extends BasePersistence<DictItemGroupTemp> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link DictItemGroupTempUtil} to access the dict item group temp persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the dict item group temps where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching dict item group temps
	*/
	public java.util.List<DictItemGroupTemp> findByUuid(String uuid);

	/**
	* Returns a range of all the dict item group temps where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemGroupTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of dict item group temps
	* @param end the upper bound of the range of dict item group temps (not inclusive)
	* @return the range of matching dict item group temps
	*/
	public java.util.List<DictItemGroupTemp> findByUuid(String uuid, int start,
		int end);

	/**
	* Returns an ordered range of all the dict item group temps where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemGroupTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of dict item group temps
	* @param end the upper bound of the range of dict item group temps (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dict item group temps
	*/
	public java.util.List<DictItemGroupTemp> findByUuid(String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<DictItemGroupTemp> orderByComparator);

	/**
	* Returns an ordered range of all the dict item group temps where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemGroupTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of dict item group temps
	* @param end the upper bound of the range of dict item group temps (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dict item group temps
	*/
	public java.util.List<DictItemGroupTemp> findByUuid(String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<DictItemGroupTemp> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first dict item group temp in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dict item group temp
	* @throws NoSuchDictItemGroupTempException if a matching dict item group temp could not be found
	*/
	public DictItemGroupTemp findByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<DictItemGroupTemp> orderByComparator)
		throws NoSuchDictItemGroupTempException;

	/**
	* Returns the first dict item group temp in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dict item group temp, or <code>null</code> if a matching dict item group temp could not be found
	*/
	public DictItemGroupTemp fetchByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<DictItemGroupTemp> orderByComparator);

	/**
	* Returns the last dict item group temp in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dict item group temp
	* @throws NoSuchDictItemGroupTempException if a matching dict item group temp could not be found
	*/
	public DictItemGroupTemp findByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<DictItemGroupTemp> orderByComparator)
		throws NoSuchDictItemGroupTempException;

	/**
	* Returns the last dict item group temp in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dict item group temp, or <code>null</code> if a matching dict item group temp could not be found
	*/
	public DictItemGroupTemp fetchByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<DictItemGroupTemp> orderByComparator);

	/**
	* Returns the dict item group temps before and after the current dict item group temp in the ordered set where uuid = &#63;.
	*
	* @param dictItemGroupId the primary key of the current dict item group temp
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dict item group temp
	* @throws NoSuchDictItemGroupTempException if a dict item group temp with the primary key could not be found
	*/
	public DictItemGroupTemp[] findByUuid_PrevAndNext(long dictItemGroupId,
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<DictItemGroupTemp> orderByComparator)
		throws NoSuchDictItemGroupTempException;

	/**
	* Removes all the dict item group temps where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(String uuid);

	/**
	* Returns the number of dict item group temps where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching dict item group temps
	*/
	public int countByUuid(String uuid);

	/**
	* Returns the dict item group temp where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchDictItemGroupTempException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching dict item group temp
	* @throws NoSuchDictItemGroupTempException if a matching dict item group temp could not be found
	*/
	public DictItemGroupTemp findByUUID_G(String uuid, long groupId)
		throws NoSuchDictItemGroupTempException;

	/**
	* Returns the dict item group temp where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching dict item group temp, or <code>null</code> if a matching dict item group temp could not be found
	*/
	public DictItemGroupTemp fetchByUUID_G(String uuid, long groupId);

	/**
	* Returns the dict item group temp where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching dict item group temp, or <code>null</code> if a matching dict item group temp could not be found
	*/
	public DictItemGroupTemp fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the dict item group temp where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the dict item group temp that was removed
	*/
	public DictItemGroupTemp removeByUUID_G(String uuid, long groupId)
		throws NoSuchDictItemGroupTempException;

	/**
	* Returns the number of dict item group temps where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching dict item group temps
	*/
	public int countByUUID_G(String uuid, long groupId);

	/**
	* Returns all the dict item group temps where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching dict item group temps
	*/
	public java.util.List<DictItemGroupTemp> findByUuid_C(String uuid,
		long companyId);

	/**
	* Returns a range of all the dict item group temps where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemGroupTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of dict item group temps
	* @param end the upper bound of the range of dict item group temps (not inclusive)
	* @return the range of matching dict item group temps
	*/
	public java.util.List<DictItemGroupTemp> findByUuid_C(String uuid,
		long companyId, int start, int end);

	/**
	* Returns an ordered range of all the dict item group temps where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemGroupTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of dict item group temps
	* @param end the upper bound of the range of dict item group temps (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dict item group temps
	*/
	public java.util.List<DictItemGroupTemp> findByUuid_C(String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DictItemGroupTemp> orderByComparator);

	/**
	* Returns an ordered range of all the dict item group temps where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemGroupTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of dict item group temps
	* @param end the upper bound of the range of dict item group temps (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dict item group temps
	*/
	public java.util.List<DictItemGroupTemp> findByUuid_C(String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DictItemGroupTemp> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first dict item group temp in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dict item group temp
	* @throws NoSuchDictItemGroupTempException if a matching dict item group temp could not be found
	*/
	public DictItemGroupTemp findByUuid_C_First(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<DictItemGroupTemp> orderByComparator)
		throws NoSuchDictItemGroupTempException;

	/**
	* Returns the first dict item group temp in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dict item group temp, or <code>null</code> if a matching dict item group temp could not be found
	*/
	public DictItemGroupTemp fetchByUuid_C_First(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<DictItemGroupTemp> orderByComparator);

	/**
	* Returns the last dict item group temp in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dict item group temp
	* @throws NoSuchDictItemGroupTempException if a matching dict item group temp could not be found
	*/
	public DictItemGroupTemp findByUuid_C_Last(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<DictItemGroupTemp> orderByComparator)
		throws NoSuchDictItemGroupTempException;

	/**
	* Returns the last dict item group temp in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dict item group temp, or <code>null</code> if a matching dict item group temp could not be found
	*/
	public DictItemGroupTemp fetchByUuid_C_Last(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<DictItemGroupTemp> orderByComparator);

	/**
	* Returns the dict item group temps before and after the current dict item group temp in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param dictItemGroupId the primary key of the current dict item group temp
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dict item group temp
	* @throws NoSuchDictItemGroupTempException if a dict item group temp with the primary key could not be found
	*/
	public DictItemGroupTemp[] findByUuid_C_PrevAndNext(long dictItemGroupId,
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<DictItemGroupTemp> orderByComparator)
		throws NoSuchDictItemGroupTempException;

	/**
	* Removes all the dict item group temps where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(String uuid, long companyId);

	/**
	* Returns the number of dict item group temps where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching dict item group temps
	*/
	public int countByUuid_C(String uuid, long companyId);

	/**
	* Returns the dict item group temp where groupId = &#63; and dictGroupId = &#63; and dictItemId = &#63; or throws a {@link NoSuchDictItemGroupTempException} if it could not be found.
	*
	* @param groupId the group ID
	* @param dictGroupId the dict group ID
	* @param dictItemId the dict item ID
	* @return the matching dict item group temp
	* @throws NoSuchDictItemGroupTempException if a matching dict item group temp could not be found
	*/
	public DictItemGroupTemp findByF_dictItemId_dictGroupId(long groupId,
		long dictGroupId, long dictItemId)
		throws NoSuchDictItemGroupTempException;

	/**
	* Returns the dict item group temp where groupId = &#63; and dictGroupId = &#63; and dictItemId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param dictGroupId the dict group ID
	* @param dictItemId the dict item ID
	* @return the matching dict item group temp, or <code>null</code> if a matching dict item group temp could not be found
	*/
	public DictItemGroupTemp fetchByF_dictItemId_dictGroupId(long groupId,
		long dictGroupId, long dictItemId);

	/**
	* Returns the dict item group temp where groupId = &#63; and dictGroupId = &#63; and dictItemId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param dictGroupId the dict group ID
	* @param dictItemId the dict item ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching dict item group temp, or <code>null</code> if a matching dict item group temp could not be found
	*/
	public DictItemGroupTemp fetchByF_dictItemId_dictGroupId(long groupId,
		long dictGroupId, long dictItemId, boolean retrieveFromCache);

	/**
	* Removes the dict item group temp where groupId = &#63; and dictGroupId = &#63; and dictItemId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param dictGroupId the dict group ID
	* @param dictItemId the dict item ID
	* @return the dict item group temp that was removed
	*/
	public DictItemGroupTemp removeByF_dictItemId_dictGroupId(long groupId,
		long dictGroupId, long dictItemId)
		throws NoSuchDictItemGroupTempException;

	/**
	* Returns the number of dict item group temps where groupId = &#63; and dictGroupId = &#63; and dictItemId = &#63;.
	*
	* @param groupId the group ID
	* @param dictGroupId the dict group ID
	* @param dictItemId the dict item ID
	* @return the number of matching dict item group temps
	*/
	public int countByF_dictItemId_dictGroupId(long groupId, long dictGroupId,
		long dictItemId);

	/**
	* Returns all the dict item group temps where groupId = &#63; and dictGroupId = &#63;.
	*
	* @param groupId the group ID
	* @param dictGroupId the dict group ID
	* @return the matching dict item group temps
	*/
	public java.util.List<DictItemGroupTemp> findByF_dictGroupId(long groupId,
		long dictGroupId);

	/**
	* Returns a range of all the dict item group temps where groupId = &#63; and dictGroupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemGroupTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param dictGroupId the dict group ID
	* @param start the lower bound of the range of dict item group temps
	* @param end the upper bound of the range of dict item group temps (not inclusive)
	* @return the range of matching dict item group temps
	*/
	public java.util.List<DictItemGroupTemp> findByF_dictGroupId(long groupId,
		long dictGroupId, int start, int end);

	/**
	* Returns an ordered range of all the dict item group temps where groupId = &#63; and dictGroupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemGroupTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param dictGroupId the dict group ID
	* @param start the lower bound of the range of dict item group temps
	* @param end the upper bound of the range of dict item group temps (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dict item group temps
	*/
	public java.util.List<DictItemGroupTemp> findByF_dictGroupId(long groupId,
		long dictGroupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DictItemGroupTemp> orderByComparator);

	/**
	* Returns an ordered range of all the dict item group temps where groupId = &#63; and dictGroupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemGroupTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param dictGroupId the dict group ID
	* @param start the lower bound of the range of dict item group temps
	* @param end the upper bound of the range of dict item group temps (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dict item group temps
	*/
	public java.util.List<DictItemGroupTemp> findByF_dictGroupId(long groupId,
		long dictGroupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DictItemGroupTemp> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first dict item group temp in the ordered set where groupId = &#63; and dictGroupId = &#63;.
	*
	* @param groupId the group ID
	* @param dictGroupId the dict group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dict item group temp
	* @throws NoSuchDictItemGroupTempException if a matching dict item group temp could not be found
	*/
	public DictItemGroupTemp findByF_dictGroupId_First(long groupId,
		long dictGroupId,
		com.liferay.portal.kernel.util.OrderByComparator<DictItemGroupTemp> orderByComparator)
		throws NoSuchDictItemGroupTempException;

	/**
	* Returns the first dict item group temp in the ordered set where groupId = &#63; and dictGroupId = &#63;.
	*
	* @param groupId the group ID
	* @param dictGroupId the dict group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dict item group temp, or <code>null</code> if a matching dict item group temp could not be found
	*/
	public DictItemGroupTemp fetchByF_dictGroupId_First(long groupId,
		long dictGroupId,
		com.liferay.portal.kernel.util.OrderByComparator<DictItemGroupTemp> orderByComparator);

	/**
	* Returns the last dict item group temp in the ordered set where groupId = &#63; and dictGroupId = &#63;.
	*
	* @param groupId the group ID
	* @param dictGroupId the dict group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dict item group temp
	* @throws NoSuchDictItemGroupTempException if a matching dict item group temp could not be found
	*/
	public DictItemGroupTemp findByF_dictGroupId_Last(long groupId,
		long dictGroupId,
		com.liferay.portal.kernel.util.OrderByComparator<DictItemGroupTemp> orderByComparator)
		throws NoSuchDictItemGroupTempException;

	/**
	* Returns the last dict item group temp in the ordered set where groupId = &#63; and dictGroupId = &#63;.
	*
	* @param groupId the group ID
	* @param dictGroupId the dict group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dict item group temp, or <code>null</code> if a matching dict item group temp could not be found
	*/
	public DictItemGroupTemp fetchByF_dictGroupId_Last(long groupId,
		long dictGroupId,
		com.liferay.portal.kernel.util.OrderByComparator<DictItemGroupTemp> orderByComparator);

	/**
	* Returns the dict item group temps before and after the current dict item group temp in the ordered set where groupId = &#63; and dictGroupId = &#63;.
	*
	* @param dictItemGroupId the primary key of the current dict item group temp
	* @param groupId the group ID
	* @param dictGroupId the dict group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dict item group temp
	* @throws NoSuchDictItemGroupTempException if a dict item group temp with the primary key could not be found
	*/
	public DictItemGroupTemp[] findByF_dictGroupId_PrevAndNext(
		long dictItemGroupId, long groupId, long dictGroupId,
		com.liferay.portal.kernel.util.OrderByComparator<DictItemGroupTemp> orderByComparator)
		throws NoSuchDictItemGroupTempException;

	/**
	* Removes all the dict item group temps where groupId = &#63; and dictGroupId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param dictGroupId the dict group ID
	*/
	public void removeByF_dictGroupId(long groupId, long dictGroupId);

	/**
	* Returns the number of dict item group temps where groupId = &#63; and dictGroupId = &#63;.
	*
	* @param groupId the group ID
	* @param dictGroupId the dict group ID
	* @return the number of matching dict item group temps
	*/
	public int countByF_dictGroupId(long groupId, long dictGroupId);

	/**
	* Returns all the dict item group temps where groupId = &#63; and dictItemId = &#63;.
	*
	* @param groupId the group ID
	* @param dictItemId the dict item ID
	* @return the matching dict item group temps
	*/
	public java.util.List<DictItemGroupTemp> findByF_dictItemId(long groupId,
		long dictItemId);

	/**
	* Returns a range of all the dict item group temps where groupId = &#63; and dictItemId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemGroupTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param dictItemId the dict item ID
	* @param start the lower bound of the range of dict item group temps
	* @param end the upper bound of the range of dict item group temps (not inclusive)
	* @return the range of matching dict item group temps
	*/
	public java.util.List<DictItemGroupTemp> findByF_dictItemId(long groupId,
		long dictItemId, int start, int end);

	/**
	* Returns an ordered range of all the dict item group temps where groupId = &#63; and dictItemId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemGroupTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param dictItemId the dict item ID
	* @param start the lower bound of the range of dict item group temps
	* @param end the upper bound of the range of dict item group temps (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dict item group temps
	*/
	public java.util.List<DictItemGroupTemp> findByF_dictItemId(long groupId,
		long dictItemId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DictItemGroupTemp> orderByComparator);

	/**
	* Returns an ordered range of all the dict item group temps where groupId = &#63; and dictItemId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemGroupTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param dictItemId the dict item ID
	* @param start the lower bound of the range of dict item group temps
	* @param end the upper bound of the range of dict item group temps (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dict item group temps
	*/
	public java.util.List<DictItemGroupTemp> findByF_dictItemId(long groupId,
		long dictItemId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DictItemGroupTemp> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first dict item group temp in the ordered set where groupId = &#63; and dictItemId = &#63;.
	*
	* @param groupId the group ID
	* @param dictItemId the dict item ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dict item group temp
	* @throws NoSuchDictItemGroupTempException if a matching dict item group temp could not be found
	*/
	public DictItemGroupTemp findByF_dictItemId_First(long groupId,
		long dictItemId,
		com.liferay.portal.kernel.util.OrderByComparator<DictItemGroupTemp> orderByComparator)
		throws NoSuchDictItemGroupTempException;

	/**
	* Returns the first dict item group temp in the ordered set where groupId = &#63; and dictItemId = &#63;.
	*
	* @param groupId the group ID
	* @param dictItemId the dict item ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dict item group temp, or <code>null</code> if a matching dict item group temp could not be found
	*/
	public DictItemGroupTemp fetchByF_dictItemId_First(long groupId,
		long dictItemId,
		com.liferay.portal.kernel.util.OrderByComparator<DictItemGroupTemp> orderByComparator);

	/**
	* Returns the last dict item group temp in the ordered set where groupId = &#63; and dictItemId = &#63;.
	*
	* @param groupId the group ID
	* @param dictItemId the dict item ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dict item group temp
	* @throws NoSuchDictItemGroupTempException if a matching dict item group temp could not be found
	*/
	public DictItemGroupTemp findByF_dictItemId_Last(long groupId,
		long dictItemId,
		com.liferay.portal.kernel.util.OrderByComparator<DictItemGroupTemp> orderByComparator)
		throws NoSuchDictItemGroupTempException;

	/**
	* Returns the last dict item group temp in the ordered set where groupId = &#63; and dictItemId = &#63;.
	*
	* @param groupId the group ID
	* @param dictItemId the dict item ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dict item group temp, or <code>null</code> if a matching dict item group temp could not be found
	*/
	public DictItemGroupTemp fetchByF_dictItemId_Last(long groupId,
		long dictItemId,
		com.liferay.portal.kernel.util.OrderByComparator<DictItemGroupTemp> orderByComparator);

	/**
	* Returns the dict item group temps before and after the current dict item group temp in the ordered set where groupId = &#63; and dictItemId = &#63;.
	*
	* @param dictItemGroupId the primary key of the current dict item group temp
	* @param groupId the group ID
	* @param dictItemId the dict item ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dict item group temp
	* @throws NoSuchDictItemGroupTempException if a dict item group temp with the primary key could not be found
	*/
	public DictItemGroupTemp[] findByF_dictItemId_PrevAndNext(
		long dictItemGroupId, long groupId, long dictItemId,
		com.liferay.portal.kernel.util.OrderByComparator<DictItemGroupTemp> orderByComparator)
		throws NoSuchDictItemGroupTempException;

	/**
	* Removes all the dict item group temps where groupId = &#63; and dictItemId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param dictItemId the dict item ID
	*/
	public void removeByF_dictItemId(long groupId, long dictItemId);

	/**
	* Returns the number of dict item group temps where groupId = &#63; and dictItemId = &#63;.
	*
	* @param groupId the group ID
	* @param dictItemId the dict item ID
	* @return the number of matching dict item group temps
	*/
	public int countByF_dictItemId(long groupId, long dictItemId);

	/**
	* Returns all the dict item group temps where modifiedDate &ge; &#63; and groupId = &#63;.
	*
	* @param modifiedDate the modified date
	* @param groupId the group ID
	* @return the matching dict item group temps
	*/
	public java.util.List<DictItemGroupTemp> findByF_newerThan(
		Date modifiedDate, long groupId);

	/**
	* Returns a range of all the dict item group temps where modifiedDate &ge; &#63; and groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemGroupTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param modifiedDate the modified date
	* @param groupId the group ID
	* @param start the lower bound of the range of dict item group temps
	* @param end the upper bound of the range of dict item group temps (not inclusive)
	* @return the range of matching dict item group temps
	*/
	public java.util.List<DictItemGroupTemp> findByF_newerThan(
		Date modifiedDate, long groupId, int start, int end);

	/**
	* Returns an ordered range of all the dict item group temps where modifiedDate &ge; &#63; and groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemGroupTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param modifiedDate the modified date
	* @param groupId the group ID
	* @param start the lower bound of the range of dict item group temps
	* @param end the upper bound of the range of dict item group temps (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dict item group temps
	*/
	public java.util.List<DictItemGroupTemp> findByF_newerThan(
		Date modifiedDate, long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DictItemGroupTemp> orderByComparator);

	/**
	* Returns an ordered range of all the dict item group temps where modifiedDate &ge; &#63; and groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemGroupTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param modifiedDate the modified date
	* @param groupId the group ID
	* @param start the lower bound of the range of dict item group temps
	* @param end the upper bound of the range of dict item group temps (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dict item group temps
	*/
	public java.util.List<DictItemGroupTemp> findByF_newerThan(
		Date modifiedDate, long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DictItemGroupTemp> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first dict item group temp in the ordered set where modifiedDate &ge; &#63; and groupId = &#63;.
	*
	* @param modifiedDate the modified date
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dict item group temp
	* @throws NoSuchDictItemGroupTempException if a matching dict item group temp could not be found
	*/
	public DictItemGroupTemp findByF_newerThan_First(Date modifiedDate,
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<DictItemGroupTemp> orderByComparator)
		throws NoSuchDictItemGroupTempException;

	/**
	* Returns the first dict item group temp in the ordered set where modifiedDate &ge; &#63; and groupId = &#63;.
	*
	* @param modifiedDate the modified date
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dict item group temp, or <code>null</code> if a matching dict item group temp could not be found
	*/
	public DictItemGroupTemp fetchByF_newerThan_First(Date modifiedDate,
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<DictItemGroupTemp> orderByComparator);

	/**
	* Returns the last dict item group temp in the ordered set where modifiedDate &ge; &#63; and groupId = &#63;.
	*
	* @param modifiedDate the modified date
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dict item group temp
	* @throws NoSuchDictItemGroupTempException if a matching dict item group temp could not be found
	*/
	public DictItemGroupTemp findByF_newerThan_Last(Date modifiedDate,
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<DictItemGroupTemp> orderByComparator)
		throws NoSuchDictItemGroupTempException;

	/**
	* Returns the last dict item group temp in the ordered set where modifiedDate &ge; &#63; and groupId = &#63;.
	*
	* @param modifiedDate the modified date
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dict item group temp, or <code>null</code> if a matching dict item group temp could not be found
	*/
	public DictItemGroupTemp fetchByF_newerThan_Last(Date modifiedDate,
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<DictItemGroupTemp> orderByComparator);

	/**
	* Returns the dict item group temps before and after the current dict item group temp in the ordered set where modifiedDate &ge; &#63; and groupId = &#63;.
	*
	* @param dictItemGroupId the primary key of the current dict item group temp
	* @param modifiedDate the modified date
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dict item group temp
	* @throws NoSuchDictItemGroupTempException if a dict item group temp with the primary key could not be found
	*/
	public DictItemGroupTemp[] findByF_newerThan_PrevAndNext(
		long dictItemGroupId, Date modifiedDate, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<DictItemGroupTemp> orderByComparator)
		throws NoSuchDictItemGroupTempException;

	/**
	* Removes all the dict item group temps where modifiedDate &ge; &#63; and groupId = &#63; from the database.
	*
	* @param modifiedDate the modified date
	* @param groupId the group ID
	*/
	public void removeByF_newerThan(Date modifiedDate, long groupId);

	/**
	* Returns the number of dict item group temps where modifiedDate &ge; &#63; and groupId = &#63;.
	*
	* @param modifiedDate the modified date
	* @param groupId the group ID
	* @return the number of matching dict item group temps
	*/
	public int countByF_newerThan(Date modifiedDate, long groupId);

	/**
	* Caches the dict item group temp in the entity cache if it is enabled.
	*
	* @param dictItemGroupTemp the dict item group temp
	*/
	public void cacheResult(DictItemGroupTemp dictItemGroupTemp);

	/**
	* Caches the dict item group temps in the entity cache if it is enabled.
	*
	* @param dictItemGroupTemps the dict item group temps
	*/
	public void cacheResult(
		java.util.List<DictItemGroupTemp> dictItemGroupTemps);

	/**
	* Creates a new dict item group temp with the primary key. Does not add the dict item group temp to the database.
	*
	* @param dictItemGroupId the primary key for the new dict item group temp
	* @return the new dict item group temp
	*/
	public DictItemGroupTemp create(long dictItemGroupId);

	/**
	* Removes the dict item group temp with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param dictItemGroupId the primary key of the dict item group temp
	* @return the dict item group temp that was removed
	* @throws NoSuchDictItemGroupTempException if a dict item group temp with the primary key could not be found
	*/
	public DictItemGroupTemp remove(long dictItemGroupId)
		throws NoSuchDictItemGroupTempException;

	public DictItemGroupTemp updateImpl(DictItemGroupTemp dictItemGroupTemp);

	/**
	* Returns the dict item group temp with the primary key or throws a {@link NoSuchDictItemGroupTempException} if it could not be found.
	*
	* @param dictItemGroupId the primary key of the dict item group temp
	* @return the dict item group temp
	* @throws NoSuchDictItemGroupTempException if a dict item group temp with the primary key could not be found
	*/
	public DictItemGroupTemp findByPrimaryKey(long dictItemGroupId)
		throws NoSuchDictItemGroupTempException;

	/**
	* Returns the dict item group temp with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param dictItemGroupId the primary key of the dict item group temp
	* @return the dict item group temp, or <code>null</code> if a dict item group temp with the primary key could not be found
	*/
	public DictItemGroupTemp fetchByPrimaryKey(long dictItemGroupId);

	@Override
	public java.util.Map<java.io.Serializable, DictItemGroupTemp> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the dict item group temps.
	*
	* @return the dict item group temps
	*/
	public java.util.List<DictItemGroupTemp> findAll();

	/**
	* Returns a range of all the dict item group temps.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemGroupTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dict item group temps
	* @param end the upper bound of the range of dict item group temps (not inclusive)
	* @return the range of dict item group temps
	*/
	public java.util.List<DictItemGroupTemp> findAll(int start, int end);

	/**
	* Returns an ordered range of all the dict item group temps.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemGroupTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dict item group temps
	* @param end the upper bound of the range of dict item group temps (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of dict item group temps
	*/
	public java.util.List<DictItemGroupTemp> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DictItemGroupTemp> orderByComparator);

	/**
	* Returns an ordered range of all the dict item group temps.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemGroupTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dict item group temps
	* @param end the upper bound of the range of dict item group temps (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of dict item group temps
	*/
	public java.util.List<DictItemGroupTemp> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DictItemGroupTemp> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the dict item group temps from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of dict item group temps.
	*
	* @return the number of dict item group temps
	*/
	public int countAll();

	@Override
	public java.util.Set<String> getBadColumnNames();
}