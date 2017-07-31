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

package org.mobilink.backend.datamgt.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.mobilink.backend.datamgt.exception.NoSuchDictItemGroupException;
import org.mobilink.backend.datamgt.model.DictItemGroup;

/**
 * The persistence interface for the dict item group service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Binhth
 * @see org.mobilink.backend.datamgt.service.persistence.impl.DictItemGroupPersistenceImpl
 * @see DictItemGroupUtil
 * @generated
 */
@ProviderType
public interface DictItemGroupPersistence extends BasePersistence<DictItemGroup> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link DictItemGroupUtil} to access the dict item group persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the dict item groups where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching dict item groups
	*/
	public java.util.List<DictItemGroup> findByUuid(java.lang.String uuid);

	/**
	* Returns a range of all the dict item groups where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of dict item groups
	* @param end the upper bound of the range of dict item groups (not inclusive)
	* @return the range of matching dict item groups
	*/
	public java.util.List<DictItemGroup> findByUuid(java.lang.String uuid,
		int start, int end);

	/**
	* Returns an ordered range of all the dict item groups where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of dict item groups
	* @param end the upper bound of the range of dict item groups (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dict item groups
	*/
	public java.util.List<DictItemGroup> findByUuid(java.lang.String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DictItemGroup> orderByComparator);

	/**
	* Returns an ordered range of all the dict item groups where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of dict item groups
	* @param end the upper bound of the range of dict item groups (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dict item groups
	*/
	public java.util.List<DictItemGroup> findByUuid(java.lang.String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DictItemGroup> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first dict item group in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dict item group
	* @throws NoSuchDictItemGroupException if a matching dict item group could not be found
	*/
	public DictItemGroup findByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<DictItemGroup> orderByComparator)
		throws NoSuchDictItemGroupException;

	/**
	* Returns the first dict item group in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dict item group, or <code>null</code> if a matching dict item group could not be found
	*/
	public DictItemGroup fetchByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<DictItemGroup> orderByComparator);

	/**
	* Returns the last dict item group in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dict item group
	* @throws NoSuchDictItemGroupException if a matching dict item group could not be found
	*/
	public DictItemGroup findByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<DictItemGroup> orderByComparator)
		throws NoSuchDictItemGroupException;

	/**
	* Returns the last dict item group in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dict item group, or <code>null</code> if a matching dict item group could not be found
	*/
	public DictItemGroup fetchByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<DictItemGroup> orderByComparator);

	/**
	* Returns the dict item groups before and after the current dict item group in the ordered set where uuid = &#63;.
	*
	* @param dictItemGroupId the primary key of the current dict item group
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dict item group
	* @throws NoSuchDictItemGroupException if a dict item group with the primary key could not be found
	*/
	public DictItemGroup[] findByUuid_PrevAndNext(long dictItemGroupId,
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<DictItemGroup> orderByComparator)
		throws NoSuchDictItemGroupException;

	/**
	* Removes all the dict item groups where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(java.lang.String uuid);

	/**
	* Returns the number of dict item groups where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching dict item groups
	*/
	public int countByUuid(java.lang.String uuid);

	/**
	* Returns the dict item group where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchDictItemGroupException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching dict item group
	* @throws NoSuchDictItemGroupException if a matching dict item group could not be found
	*/
	public DictItemGroup findByUUID_G(java.lang.String uuid, long groupId)
		throws NoSuchDictItemGroupException;

	/**
	* Returns the dict item group where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching dict item group, or <code>null</code> if a matching dict item group could not be found
	*/
	public DictItemGroup fetchByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns the dict item group where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching dict item group, or <code>null</code> if a matching dict item group could not be found
	*/
	public DictItemGroup fetchByUUID_G(java.lang.String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the dict item group where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the dict item group that was removed
	*/
	public DictItemGroup removeByUUID_G(java.lang.String uuid, long groupId)
		throws NoSuchDictItemGroupException;

	/**
	* Returns the number of dict item groups where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching dict item groups
	*/
	public int countByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns all the dict item groups where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching dict item groups
	*/
	public java.util.List<DictItemGroup> findByUuid_C(java.lang.String uuid,
		long companyId);

	/**
	* Returns a range of all the dict item groups where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of dict item groups
	* @param end the upper bound of the range of dict item groups (not inclusive)
	* @return the range of matching dict item groups
	*/
	public java.util.List<DictItemGroup> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end);

	/**
	* Returns an ordered range of all the dict item groups where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of dict item groups
	* @param end the upper bound of the range of dict item groups (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dict item groups
	*/
	public java.util.List<DictItemGroup> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DictItemGroup> orderByComparator);

	/**
	* Returns an ordered range of all the dict item groups where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of dict item groups
	* @param end the upper bound of the range of dict item groups (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dict item groups
	*/
	public java.util.List<DictItemGroup> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DictItemGroup> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first dict item group in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dict item group
	* @throws NoSuchDictItemGroupException if a matching dict item group could not be found
	*/
	public DictItemGroup findByUuid_C_First(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<DictItemGroup> orderByComparator)
		throws NoSuchDictItemGroupException;

	/**
	* Returns the first dict item group in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dict item group, or <code>null</code> if a matching dict item group could not be found
	*/
	public DictItemGroup fetchByUuid_C_First(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<DictItemGroup> orderByComparator);

	/**
	* Returns the last dict item group in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dict item group
	* @throws NoSuchDictItemGroupException if a matching dict item group could not be found
	*/
	public DictItemGroup findByUuid_C_Last(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<DictItemGroup> orderByComparator)
		throws NoSuchDictItemGroupException;

	/**
	* Returns the last dict item group in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dict item group, or <code>null</code> if a matching dict item group could not be found
	*/
	public DictItemGroup fetchByUuid_C_Last(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<DictItemGroup> orderByComparator);

	/**
	* Returns the dict item groups before and after the current dict item group in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param dictItemGroupId the primary key of the current dict item group
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dict item group
	* @throws NoSuchDictItemGroupException if a dict item group with the primary key could not be found
	*/
	public DictItemGroup[] findByUuid_C_PrevAndNext(long dictItemGroupId,
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<DictItemGroup> orderByComparator)
		throws NoSuchDictItemGroupException;

	/**
	* Removes all the dict item groups where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns the number of dict item groups where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching dict item groups
	*/
	public int countByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Caches the dict item group in the entity cache if it is enabled.
	*
	* @param dictItemGroup the dict item group
	*/
	public void cacheResult(DictItemGroup dictItemGroup);

	/**
	* Caches the dict item groups in the entity cache if it is enabled.
	*
	* @param dictItemGroups the dict item groups
	*/
	public void cacheResult(java.util.List<DictItemGroup> dictItemGroups);

	/**
	* Creates a new dict item group with the primary key. Does not add the dict item group to the database.
	*
	* @param dictItemGroupId the primary key for the new dict item group
	* @return the new dict item group
	*/
	public DictItemGroup create(long dictItemGroupId);

	/**
	* Removes the dict item group with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param dictItemGroupId the primary key of the dict item group
	* @return the dict item group that was removed
	* @throws NoSuchDictItemGroupException if a dict item group with the primary key could not be found
	*/
	public DictItemGroup remove(long dictItemGroupId)
		throws NoSuchDictItemGroupException;

	public DictItemGroup updateImpl(DictItemGroup dictItemGroup);

	/**
	* Returns the dict item group with the primary key or throws a {@link NoSuchDictItemGroupException} if it could not be found.
	*
	* @param dictItemGroupId the primary key of the dict item group
	* @return the dict item group
	* @throws NoSuchDictItemGroupException if a dict item group with the primary key could not be found
	*/
	public DictItemGroup findByPrimaryKey(long dictItemGroupId)
		throws NoSuchDictItemGroupException;

	/**
	* Returns the dict item group with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param dictItemGroupId the primary key of the dict item group
	* @return the dict item group, or <code>null</code> if a dict item group with the primary key could not be found
	*/
	public DictItemGroup fetchByPrimaryKey(long dictItemGroupId);

	@Override
	public java.util.Map<java.io.Serializable, DictItemGroup> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the dict item groups.
	*
	* @return the dict item groups
	*/
	public java.util.List<DictItemGroup> findAll();

	/**
	* Returns a range of all the dict item groups.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dict item groups
	* @param end the upper bound of the range of dict item groups (not inclusive)
	* @return the range of dict item groups
	*/
	public java.util.List<DictItemGroup> findAll(int start, int end);

	/**
	* Returns an ordered range of all the dict item groups.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dict item groups
	* @param end the upper bound of the range of dict item groups (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of dict item groups
	*/
	public java.util.List<DictItemGroup> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DictItemGroup> orderByComparator);

	/**
	* Returns an ordered range of all the dict item groups.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dict item groups
	* @param end the upper bound of the range of dict item groups (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of dict item groups
	*/
	public java.util.List<DictItemGroup> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DictItemGroup> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the dict item groups from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of dict item groups.
	*
	* @return the number of dict item groups
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}