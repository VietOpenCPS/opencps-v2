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

import org.mobilink.backend.datamgt.exception.NoSuchDictGroupException;
import org.mobilink.backend.datamgt.model.DictGroup;

/**
 * The persistence interface for the dict group service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Binhth
 * @see org.mobilink.backend.datamgt.service.persistence.impl.DictGroupPersistenceImpl
 * @see DictGroupUtil
 * @generated
 */
@ProviderType
public interface DictGroupPersistence extends BasePersistence<DictGroup> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link DictGroupUtil} to access the dict group persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the dict groups where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching dict groups
	*/
	public java.util.List<DictGroup> findByUuid(java.lang.String uuid);

	/**
	* Returns a range of all the dict groups where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of dict groups
	* @param end the upper bound of the range of dict groups (not inclusive)
	* @return the range of matching dict groups
	*/
	public java.util.List<DictGroup> findByUuid(java.lang.String uuid,
		int start, int end);

	/**
	* Returns an ordered range of all the dict groups where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of dict groups
	* @param end the upper bound of the range of dict groups (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dict groups
	*/
	public java.util.List<DictGroup> findByUuid(java.lang.String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DictGroup> orderByComparator);

	/**
	* Returns an ordered range of all the dict groups where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of dict groups
	* @param end the upper bound of the range of dict groups (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dict groups
	*/
	public java.util.List<DictGroup> findByUuid(java.lang.String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DictGroup> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first dict group in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dict group
	* @throws NoSuchDictGroupException if a matching dict group could not be found
	*/
	public DictGroup findByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<DictGroup> orderByComparator)
		throws NoSuchDictGroupException;

	/**
	* Returns the first dict group in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dict group, or <code>null</code> if a matching dict group could not be found
	*/
	public DictGroup fetchByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<DictGroup> orderByComparator);

	/**
	* Returns the last dict group in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dict group
	* @throws NoSuchDictGroupException if a matching dict group could not be found
	*/
	public DictGroup findByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<DictGroup> orderByComparator)
		throws NoSuchDictGroupException;

	/**
	* Returns the last dict group in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dict group, or <code>null</code> if a matching dict group could not be found
	*/
	public DictGroup fetchByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<DictGroup> orderByComparator);

	/**
	* Returns the dict groups before and after the current dict group in the ordered set where uuid = &#63;.
	*
	* @param dictGroupId the primary key of the current dict group
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dict group
	* @throws NoSuchDictGroupException if a dict group with the primary key could not be found
	*/
	public DictGroup[] findByUuid_PrevAndNext(long dictGroupId,
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<DictGroup> orderByComparator)
		throws NoSuchDictGroupException;

	/**
	* Removes all the dict groups where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(java.lang.String uuid);

	/**
	* Returns the number of dict groups where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching dict groups
	*/
	public int countByUuid(java.lang.String uuid);

	/**
	* Returns the dict group where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchDictGroupException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching dict group
	* @throws NoSuchDictGroupException if a matching dict group could not be found
	*/
	public DictGroup findByUUID_G(java.lang.String uuid, long groupId)
		throws NoSuchDictGroupException;

	/**
	* Returns the dict group where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching dict group, or <code>null</code> if a matching dict group could not be found
	*/
	public DictGroup fetchByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns the dict group where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching dict group, or <code>null</code> if a matching dict group could not be found
	*/
	public DictGroup fetchByUUID_G(java.lang.String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the dict group where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the dict group that was removed
	*/
	public DictGroup removeByUUID_G(java.lang.String uuid, long groupId)
		throws NoSuchDictGroupException;

	/**
	* Returns the number of dict groups where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching dict groups
	*/
	public int countByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns all the dict groups where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching dict groups
	*/
	public java.util.List<DictGroup> findByUuid_C(java.lang.String uuid,
		long companyId);

	/**
	* Returns a range of all the dict groups where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of dict groups
	* @param end the upper bound of the range of dict groups (not inclusive)
	* @return the range of matching dict groups
	*/
	public java.util.List<DictGroup> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end);

	/**
	* Returns an ordered range of all the dict groups where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of dict groups
	* @param end the upper bound of the range of dict groups (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dict groups
	*/
	public java.util.List<DictGroup> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DictGroup> orderByComparator);

	/**
	* Returns an ordered range of all the dict groups where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of dict groups
	* @param end the upper bound of the range of dict groups (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dict groups
	*/
	public java.util.List<DictGroup> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DictGroup> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first dict group in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dict group
	* @throws NoSuchDictGroupException if a matching dict group could not be found
	*/
	public DictGroup findByUuid_C_First(java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<DictGroup> orderByComparator)
		throws NoSuchDictGroupException;

	/**
	* Returns the first dict group in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dict group, or <code>null</code> if a matching dict group could not be found
	*/
	public DictGroup fetchByUuid_C_First(java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<DictGroup> orderByComparator);

	/**
	* Returns the last dict group in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dict group
	* @throws NoSuchDictGroupException if a matching dict group could not be found
	*/
	public DictGroup findByUuid_C_Last(java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<DictGroup> orderByComparator)
		throws NoSuchDictGroupException;

	/**
	* Returns the last dict group in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dict group, or <code>null</code> if a matching dict group could not be found
	*/
	public DictGroup fetchByUuid_C_Last(java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<DictGroup> orderByComparator);

	/**
	* Returns the dict groups before and after the current dict group in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param dictGroupId the primary key of the current dict group
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dict group
	* @throws NoSuchDictGroupException if a dict group with the primary key could not be found
	*/
	public DictGroup[] findByUuid_C_PrevAndNext(long dictGroupId,
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<DictGroup> orderByComparator)
		throws NoSuchDictGroupException;

	/**
	* Removes all the dict groups where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns the number of dict groups where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching dict groups
	*/
	public int countByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Caches the dict group in the entity cache if it is enabled.
	*
	* @param dictGroup the dict group
	*/
	public void cacheResult(DictGroup dictGroup);

	/**
	* Caches the dict groups in the entity cache if it is enabled.
	*
	* @param dictGroups the dict groups
	*/
	public void cacheResult(java.util.List<DictGroup> dictGroups);

	/**
	* Creates a new dict group with the primary key. Does not add the dict group to the database.
	*
	* @param dictGroupId the primary key for the new dict group
	* @return the new dict group
	*/
	public DictGroup create(long dictGroupId);

	/**
	* Removes the dict group with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param dictGroupId the primary key of the dict group
	* @return the dict group that was removed
	* @throws NoSuchDictGroupException if a dict group with the primary key could not be found
	*/
	public DictGroup remove(long dictGroupId) throws NoSuchDictGroupException;

	public DictGroup updateImpl(DictGroup dictGroup);

	/**
	* Returns the dict group with the primary key or throws a {@link NoSuchDictGroupException} if it could not be found.
	*
	* @param dictGroupId the primary key of the dict group
	* @return the dict group
	* @throws NoSuchDictGroupException if a dict group with the primary key could not be found
	*/
	public DictGroup findByPrimaryKey(long dictGroupId)
		throws NoSuchDictGroupException;

	/**
	* Returns the dict group with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param dictGroupId the primary key of the dict group
	* @return the dict group, or <code>null</code> if a dict group with the primary key could not be found
	*/
	public DictGroup fetchByPrimaryKey(long dictGroupId);

	@Override
	public java.util.Map<java.io.Serializable, DictGroup> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the dict groups.
	*
	* @return the dict groups
	*/
	public java.util.List<DictGroup> findAll();

	/**
	* Returns a range of all the dict groups.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dict groups
	* @param end the upper bound of the range of dict groups (not inclusive)
	* @return the range of dict groups
	*/
	public java.util.List<DictGroup> findAll(int start, int end);

	/**
	* Returns an ordered range of all the dict groups.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dict groups
	* @param end the upper bound of the range of dict groups (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of dict groups
	*/
	public java.util.List<DictGroup> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DictGroup> orderByComparator);

	/**
	* Returns an ordered range of all the dict groups.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dict groups
	* @param end the upper bound of the range of dict groups (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of dict groups
	*/
	public java.util.List<DictGroup> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DictGroup> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the dict groups from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of dict groups.
	*
	* @return the number of dict groups
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}