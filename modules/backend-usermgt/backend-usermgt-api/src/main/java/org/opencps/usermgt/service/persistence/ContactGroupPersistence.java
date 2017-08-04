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

import org.opencps.usermgt.exception.NoSuchContactGroupException;
import org.opencps.usermgt.model.ContactGroup;

/**
 * The persistence interface for the contact group service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Binhth
 * @see org.mobilink.backend.usermgt.service.persistence.impl.ContactGroupPersistenceImpl
 * @see ContactGroupUtil
 * @generated
 */
@ProviderType
public interface ContactGroupPersistence extends BasePersistence<ContactGroup> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ContactGroupUtil} to access the contact group persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the contact groups where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching contact groups
	*/
	public java.util.List<ContactGroup> findByUuid(java.lang.String uuid);

	/**
	* Returns a range of all the contact groups where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContactGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of contact groups
	* @param end the upper bound of the range of contact groups (not inclusive)
	* @return the range of matching contact groups
	*/
	public java.util.List<ContactGroup> findByUuid(java.lang.String uuid,
		int start, int end);

	/**
	* Returns an ordered range of all the contact groups where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContactGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of contact groups
	* @param end the upper bound of the range of contact groups (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching contact groups
	*/
	public java.util.List<ContactGroup> findByUuid(java.lang.String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ContactGroup> orderByComparator);

	/**
	* Returns an ordered range of all the contact groups where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContactGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of contact groups
	* @param end the upper bound of the range of contact groups (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching contact groups
	*/
	public java.util.List<ContactGroup> findByUuid(java.lang.String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ContactGroup> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first contact group in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching contact group
	* @throws NoSuchContactGroupException if a matching contact group could not be found
	*/
	public ContactGroup findByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ContactGroup> orderByComparator)
		throws NoSuchContactGroupException;

	/**
	* Returns the first contact group in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching contact group, or <code>null</code> if a matching contact group could not be found
	*/
	public ContactGroup fetchByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ContactGroup> orderByComparator);

	/**
	* Returns the last contact group in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching contact group
	* @throws NoSuchContactGroupException if a matching contact group could not be found
	*/
	public ContactGroup findByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ContactGroup> orderByComparator)
		throws NoSuchContactGroupException;

	/**
	* Returns the last contact group in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching contact group, or <code>null</code> if a matching contact group could not be found
	*/
	public ContactGroup fetchByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ContactGroup> orderByComparator);

	/**
	* Returns the contact groups before and after the current contact group in the ordered set where uuid = &#63;.
	*
	* @param contactGroupId the primary key of the current contact group
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next contact group
	* @throws NoSuchContactGroupException if a contact group with the primary key could not be found
	*/
	public ContactGroup[] findByUuid_PrevAndNext(long contactGroupId,
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ContactGroup> orderByComparator)
		throws NoSuchContactGroupException;

	/**
	* Removes all the contact groups where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(java.lang.String uuid);

	/**
	* Returns the number of contact groups where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching contact groups
	*/
	public int countByUuid(java.lang.String uuid);

	/**
	* Returns the contact group where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchContactGroupException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching contact group
	* @throws NoSuchContactGroupException if a matching contact group could not be found
	*/
	public ContactGroup findByUUID_G(java.lang.String uuid, long groupId)
		throws NoSuchContactGroupException;

	/**
	* Returns the contact group where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching contact group, or <code>null</code> if a matching contact group could not be found
	*/
	public ContactGroup fetchByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns the contact group where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching contact group, or <code>null</code> if a matching contact group could not be found
	*/
	public ContactGroup fetchByUUID_G(java.lang.String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the contact group where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the contact group that was removed
	*/
	public ContactGroup removeByUUID_G(java.lang.String uuid, long groupId)
		throws NoSuchContactGroupException;

	/**
	* Returns the number of contact groups where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching contact groups
	*/
	public int countByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns all the contact groups where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching contact groups
	*/
	public java.util.List<ContactGroup> findByUuid_C(java.lang.String uuid,
		long companyId);

	/**
	* Returns a range of all the contact groups where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContactGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of contact groups
	* @param end the upper bound of the range of contact groups (not inclusive)
	* @return the range of matching contact groups
	*/
	public java.util.List<ContactGroup> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end);

	/**
	* Returns an ordered range of all the contact groups where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContactGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of contact groups
	* @param end the upper bound of the range of contact groups (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching contact groups
	*/
	public java.util.List<ContactGroup> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ContactGroup> orderByComparator);

	/**
	* Returns an ordered range of all the contact groups where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContactGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of contact groups
	* @param end the upper bound of the range of contact groups (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching contact groups
	*/
	public java.util.List<ContactGroup> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ContactGroup> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first contact group in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching contact group
	* @throws NoSuchContactGroupException if a matching contact group could not be found
	*/
	public ContactGroup findByUuid_C_First(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ContactGroup> orderByComparator)
		throws NoSuchContactGroupException;

	/**
	* Returns the first contact group in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching contact group, or <code>null</code> if a matching contact group could not be found
	*/
	public ContactGroup fetchByUuid_C_First(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ContactGroup> orderByComparator);

	/**
	* Returns the last contact group in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching contact group
	* @throws NoSuchContactGroupException if a matching contact group could not be found
	*/
	public ContactGroup findByUuid_C_Last(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ContactGroup> orderByComparator)
		throws NoSuchContactGroupException;

	/**
	* Returns the last contact group in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching contact group, or <code>null</code> if a matching contact group could not be found
	*/
	public ContactGroup fetchByUuid_C_Last(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ContactGroup> orderByComparator);

	/**
	* Returns the contact groups before and after the current contact group in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param contactGroupId the primary key of the current contact group
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next contact group
	* @throws NoSuchContactGroupException if a contact group with the primary key could not be found
	*/
	public ContactGroup[] findByUuid_C_PrevAndNext(long contactGroupId,
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ContactGroup> orderByComparator)
		throws NoSuchContactGroupException;

	/**
	* Removes all the contact groups where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns the number of contact groups where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching contact groups
	*/
	public int countByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Caches the contact group in the entity cache if it is enabled.
	*
	* @param contactGroup the contact group
	*/
	public void cacheResult(ContactGroup contactGroup);

	/**
	* Caches the contact groups in the entity cache if it is enabled.
	*
	* @param contactGroups the contact groups
	*/
	public void cacheResult(java.util.List<ContactGroup> contactGroups);

	/**
	* Creates a new contact group with the primary key. Does not add the contact group to the database.
	*
	* @param contactGroupId the primary key for the new contact group
	* @return the new contact group
	*/
	public ContactGroup create(long contactGroupId);

	/**
	* Removes the contact group with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param contactGroupId the primary key of the contact group
	* @return the contact group that was removed
	* @throws NoSuchContactGroupException if a contact group with the primary key could not be found
	*/
	public ContactGroup remove(long contactGroupId)
		throws NoSuchContactGroupException;

	public ContactGroup updateImpl(ContactGroup contactGroup);

	/**
	* Returns the contact group with the primary key or throws a {@link NoSuchContactGroupException} if it could not be found.
	*
	* @param contactGroupId the primary key of the contact group
	* @return the contact group
	* @throws NoSuchContactGroupException if a contact group with the primary key could not be found
	*/
	public ContactGroup findByPrimaryKey(long contactGroupId)
		throws NoSuchContactGroupException;

	/**
	* Returns the contact group with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param contactGroupId the primary key of the contact group
	* @return the contact group, or <code>null</code> if a contact group with the primary key could not be found
	*/
	public ContactGroup fetchByPrimaryKey(long contactGroupId);

	@Override
	public java.util.Map<java.io.Serializable, ContactGroup> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the contact groups.
	*
	* @return the contact groups
	*/
	public java.util.List<ContactGroup> findAll();

	/**
	* Returns a range of all the contact groups.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContactGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of contact groups
	* @param end the upper bound of the range of contact groups (not inclusive)
	* @return the range of contact groups
	*/
	public java.util.List<ContactGroup> findAll(int start, int end);

	/**
	* Returns an ordered range of all the contact groups.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContactGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of contact groups
	* @param end the upper bound of the range of contact groups (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of contact groups
	*/
	public java.util.List<ContactGroup> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ContactGroup> orderByComparator);

	/**
	* Returns an ordered range of all the contact groups.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContactGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of contact groups
	* @param end the upper bound of the range of contact groups (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of contact groups
	*/
	public java.util.List<ContactGroup> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ContactGroup> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the contact groups from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of contact groups.
	*
	* @return the number of contact groups
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}