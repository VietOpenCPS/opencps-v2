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

package org.opencps.usermgt.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.opencps.usermgt.exception.NoSuchVisibilityException;
import org.opencps.usermgt.model.Visibility;

/**
 * The persistence interface for the visibility service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author khoavu
 * @see org.opencps.usermgt.service.persistence.impl.VisibilityPersistenceImpl
 * @see VisibilityUtil
 * @generated
 */
@ProviderType
public interface VisibilityPersistence extends BasePersistence<Visibility> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link VisibilityUtil} to access the visibility persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the visibilities where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching visibilities
	*/
	public java.util.List<Visibility> findByUuid(String uuid);

	/**
	* Returns a range of all the visibilities where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VisibilityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of visibilities
	* @param end the upper bound of the range of visibilities (not inclusive)
	* @return the range of matching visibilities
	*/
	public java.util.List<Visibility> findByUuid(String uuid, int start, int end);

	/**
	* Returns an ordered range of all the visibilities where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VisibilityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of visibilities
	* @param end the upper bound of the range of visibilities (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching visibilities
	*/
	public java.util.List<Visibility> findByUuid(String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Visibility> orderByComparator);

	/**
	* Returns an ordered range of all the visibilities where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VisibilityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of visibilities
	* @param end the upper bound of the range of visibilities (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching visibilities
	*/
	public java.util.List<Visibility> findByUuid(String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Visibility> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first visibility in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching visibility
	* @throws NoSuchVisibilityException if a matching visibility could not be found
	*/
	public Visibility findByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Visibility> orderByComparator)
		throws NoSuchVisibilityException;

	/**
	* Returns the first visibility in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching visibility, or <code>null</code> if a matching visibility could not be found
	*/
	public Visibility fetchByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Visibility> orderByComparator);

	/**
	* Returns the last visibility in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching visibility
	* @throws NoSuchVisibilityException if a matching visibility could not be found
	*/
	public Visibility findByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Visibility> orderByComparator)
		throws NoSuchVisibilityException;

	/**
	* Returns the last visibility in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching visibility, or <code>null</code> if a matching visibility could not be found
	*/
	public Visibility fetchByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Visibility> orderByComparator);

	/**
	* Returns the visibilities before and after the current visibility in the ordered set where uuid = &#63;.
	*
	* @param visibilityId the primary key of the current visibility
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next visibility
	* @throws NoSuchVisibilityException if a visibility with the primary key could not be found
	*/
	public Visibility[] findByUuid_PrevAndNext(long visibilityId, String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Visibility> orderByComparator)
		throws NoSuchVisibilityException;

	/**
	* Removes all the visibilities where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(String uuid);

	/**
	* Returns the number of visibilities where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching visibilities
	*/
	public int countByUuid(String uuid);

	/**
	* Returns the visibility where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchVisibilityException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching visibility
	* @throws NoSuchVisibilityException if a matching visibility could not be found
	*/
	public Visibility findByUUID_G(String uuid, long groupId)
		throws NoSuchVisibilityException;

	/**
	* Returns the visibility where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching visibility, or <code>null</code> if a matching visibility could not be found
	*/
	public Visibility fetchByUUID_G(String uuid, long groupId);

	/**
	* Returns the visibility where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching visibility, or <code>null</code> if a matching visibility could not be found
	*/
	public Visibility fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the visibility where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the visibility that was removed
	*/
	public Visibility removeByUUID_G(String uuid, long groupId)
		throws NoSuchVisibilityException;

	/**
	* Returns the number of visibilities where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching visibilities
	*/
	public int countByUUID_G(String uuid, long groupId);

	/**
	* Returns all the visibilities where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching visibilities
	*/
	public java.util.List<Visibility> findByUuid_C(String uuid, long companyId);

	/**
	* Returns a range of all the visibilities where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VisibilityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of visibilities
	* @param end the upper bound of the range of visibilities (not inclusive)
	* @return the range of matching visibilities
	*/
	public java.util.List<Visibility> findByUuid_C(String uuid, long companyId,
		int start, int end);

	/**
	* Returns an ordered range of all the visibilities where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VisibilityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of visibilities
	* @param end the upper bound of the range of visibilities (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching visibilities
	*/
	public java.util.List<Visibility> findByUuid_C(String uuid, long companyId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Visibility> orderByComparator);

	/**
	* Returns an ordered range of all the visibilities where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VisibilityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of visibilities
	* @param end the upper bound of the range of visibilities (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching visibilities
	*/
	public java.util.List<Visibility> findByUuid_C(String uuid, long companyId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Visibility> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first visibility in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching visibility
	* @throws NoSuchVisibilityException if a matching visibility could not be found
	*/
	public Visibility findByUuid_C_First(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Visibility> orderByComparator)
		throws NoSuchVisibilityException;

	/**
	* Returns the first visibility in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching visibility, or <code>null</code> if a matching visibility could not be found
	*/
	public Visibility fetchByUuid_C_First(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Visibility> orderByComparator);

	/**
	* Returns the last visibility in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching visibility
	* @throws NoSuchVisibilityException if a matching visibility could not be found
	*/
	public Visibility findByUuid_C_Last(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Visibility> orderByComparator)
		throws NoSuchVisibilityException;

	/**
	* Returns the last visibility in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching visibility, or <code>null</code> if a matching visibility could not be found
	*/
	public Visibility fetchByUuid_C_Last(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Visibility> orderByComparator);

	/**
	* Returns the visibilities before and after the current visibility in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param visibilityId the primary key of the current visibility
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next visibility
	* @throws NoSuchVisibilityException if a visibility with the primary key could not be found
	*/
	public Visibility[] findByUuid_C_PrevAndNext(long visibilityId,
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Visibility> orderByComparator)
		throws NoSuchVisibilityException;

	/**
	* Removes all the visibilities where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(String uuid, long companyId);

	/**
	* Returns the number of visibilities where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching visibilities
	*/
	public int countByUuid_C(String uuid, long companyId);

	/**
	* Caches the visibility in the entity cache if it is enabled.
	*
	* @param visibility the visibility
	*/
	public void cacheResult(Visibility visibility);

	/**
	* Caches the visibilities in the entity cache if it is enabled.
	*
	* @param visibilities the visibilities
	*/
	public void cacheResult(java.util.List<Visibility> visibilities);

	/**
	* Creates a new visibility with the primary key. Does not add the visibility to the database.
	*
	* @param visibilityId the primary key for the new visibility
	* @return the new visibility
	*/
	public Visibility create(long visibilityId);

	/**
	* Removes the visibility with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param visibilityId the primary key of the visibility
	* @return the visibility that was removed
	* @throws NoSuchVisibilityException if a visibility with the primary key could not be found
	*/
	public Visibility remove(long visibilityId)
		throws NoSuchVisibilityException;

	public Visibility updateImpl(Visibility visibility);

	/**
	* Returns the visibility with the primary key or throws a {@link NoSuchVisibilityException} if it could not be found.
	*
	* @param visibilityId the primary key of the visibility
	* @return the visibility
	* @throws NoSuchVisibilityException if a visibility with the primary key could not be found
	*/
	public Visibility findByPrimaryKey(long visibilityId)
		throws NoSuchVisibilityException;

	/**
	* Returns the visibility with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param visibilityId the primary key of the visibility
	* @return the visibility, or <code>null</code> if a visibility with the primary key could not be found
	*/
	public Visibility fetchByPrimaryKey(long visibilityId);

	@Override
	public java.util.Map<java.io.Serializable, Visibility> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the visibilities.
	*
	* @return the visibilities
	*/
	public java.util.List<Visibility> findAll();

	/**
	* Returns a range of all the visibilities.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VisibilityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of visibilities
	* @param end the upper bound of the range of visibilities (not inclusive)
	* @return the range of visibilities
	*/
	public java.util.List<Visibility> findAll(int start, int end);

	/**
	* Returns an ordered range of all the visibilities.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VisibilityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of visibilities
	* @param end the upper bound of the range of visibilities (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of visibilities
	*/
	public java.util.List<Visibility> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Visibility> orderByComparator);

	/**
	* Returns an ordered range of all the visibilities.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VisibilityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of visibilities
	* @param end the upper bound of the range of visibilities (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of visibilities
	*/
	public java.util.List<Visibility> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Visibility> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the visibilities from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of visibilities.
	*
	* @return the number of visibilities
	*/
	public int countAll();

	@Override
	public java.util.Set<String> getBadColumnNames();
}