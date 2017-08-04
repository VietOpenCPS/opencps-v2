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

import com.liferay.osgi.util.ServiceTrackerFactory;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.opencps.usermgt.model.ContactGroup;
import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the contact group service. This utility wraps {@link org.mobilink.backend.usermgt.service.persistence.impl.ContactGroupPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Binhth
 * @see ContactGroupPersistence
 * @see org.mobilink.backend.usermgt.service.persistence.impl.ContactGroupPersistenceImpl
 * @generated
 */
@ProviderType
public class ContactGroupUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(ContactGroup contactGroup) {
		getPersistence().clearCache(contactGroup);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<ContactGroup> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<ContactGroup> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<ContactGroup> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<ContactGroup> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static ContactGroup update(ContactGroup contactGroup) {
		return getPersistence().update(contactGroup);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static ContactGroup update(ContactGroup contactGroup,
		ServiceContext serviceContext) {
		return getPersistence().update(contactGroup, serviceContext);
	}

	/**
	* Returns all the contact groups where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching contact groups
	*/
	public static List<ContactGroup> findByUuid(java.lang.String uuid) {
		return getPersistence().findByUuid(uuid);
	}

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
	public static List<ContactGroup> findByUuid(java.lang.String uuid,
		int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

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
	public static List<ContactGroup> findByUuid(java.lang.String uuid,
		int start, int end, OrderByComparator<ContactGroup> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

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
	public static List<ContactGroup> findByUuid(java.lang.String uuid,
		int start, int end, OrderByComparator<ContactGroup> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first contact group in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching contact group
	* @throws NoSuchContactGroupException if a matching contact group could not be found
	*/
	public static ContactGroup findByUuid_First(java.lang.String uuid,
		OrderByComparator<ContactGroup> orderByComparator)
		throws org.opencps.usermgt.exception.NoSuchContactGroupException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first contact group in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching contact group, or <code>null</code> if a matching contact group could not be found
	*/
	public static ContactGroup fetchByUuid_First(java.lang.String uuid,
		OrderByComparator<ContactGroup> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last contact group in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching contact group
	* @throws NoSuchContactGroupException if a matching contact group could not be found
	*/
	public static ContactGroup findByUuid_Last(java.lang.String uuid,
		OrderByComparator<ContactGroup> orderByComparator)
		throws org.opencps.usermgt.exception.NoSuchContactGroupException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last contact group in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching contact group, or <code>null</code> if a matching contact group could not be found
	*/
	public static ContactGroup fetchByUuid_Last(java.lang.String uuid,
		OrderByComparator<ContactGroup> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the contact groups before and after the current contact group in the ordered set where uuid = &#63;.
	*
	* @param contactGroupId the primary key of the current contact group
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next contact group
	* @throws NoSuchContactGroupException if a contact group with the primary key could not be found
	*/
	public static ContactGroup[] findByUuid_PrevAndNext(long contactGroupId,
		java.lang.String uuid, OrderByComparator<ContactGroup> orderByComparator)
		throws org.opencps.usermgt.exception.NoSuchContactGroupException {
		return getPersistence()
				   .findByUuid_PrevAndNext(contactGroupId, uuid,
			orderByComparator);
	}

	/**
	* Removes all the contact groups where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(java.lang.String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of contact groups where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching contact groups
	*/
	public static int countByUuid(java.lang.String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the contact group where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchContactGroupException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching contact group
	* @throws NoSuchContactGroupException if a matching contact group could not be found
	*/
	public static ContactGroup findByUUID_G(java.lang.String uuid, long groupId)
		throws org.opencps.usermgt.exception.NoSuchContactGroupException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the contact group where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching contact group, or <code>null</code> if a matching contact group could not be found
	*/
	public static ContactGroup fetchByUUID_G(java.lang.String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the contact group where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching contact group, or <code>null</code> if a matching contact group could not be found
	*/
	public static ContactGroup fetchByUUID_G(java.lang.String uuid,
		long groupId, boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the contact group where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the contact group that was removed
	*/
	public static ContactGroup removeByUUID_G(java.lang.String uuid,
		long groupId)
		throws org.opencps.usermgt.exception.NoSuchContactGroupException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of contact groups where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching contact groups
	*/
	public static int countByUUID_G(java.lang.String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the contact groups where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching contact groups
	*/
	public static List<ContactGroup> findByUuid_C(java.lang.String uuid,
		long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

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
	public static List<ContactGroup> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end) {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

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
	public static List<ContactGroup> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		OrderByComparator<ContactGroup> orderByComparator) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

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
	public static List<ContactGroup> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		OrderByComparator<ContactGroup> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first contact group in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching contact group
	* @throws NoSuchContactGroupException if a matching contact group could not be found
	*/
	public static ContactGroup findByUuid_C_First(java.lang.String uuid,
		long companyId, OrderByComparator<ContactGroup> orderByComparator)
		throws org.opencps.usermgt.exception.NoSuchContactGroupException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first contact group in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching contact group, or <code>null</code> if a matching contact group could not be found
	*/
	public static ContactGroup fetchByUuid_C_First(java.lang.String uuid,
		long companyId, OrderByComparator<ContactGroup> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last contact group in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching contact group
	* @throws NoSuchContactGroupException if a matching contact group could not be found
	*/
	public static ContactGroup findByUuid_C_Last(java.lang.String uuid,
		long companyId, OrderByComparator<ContactGroup> orderByComparator)
		throws org.opencps.usermgt.exception.NoSuchContactGroupException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last contact group in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching contact group, or <code>null</code> if a matching contact group could not be found
	*/
	public static ContactGroup fetchByUuid_C_Last(java.lang.String uuid,
		long companyId, OrderByComparator<ContactGroup> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

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
	public static ContactGroup[] findByUuid_C_PrevAndNext(long contactGroupId,
		java.lang.String uuid, long companyId,
		OrderByComparator<ContactGroup> orderByComparator)
		throws org.opencps.usermgt.exception.NoSuchContactGroupException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(contactGroupId, uuid, companyId,
			orderByComparator);
	}

	/**
	* Removes all the contact groups where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public static void removeByUuid_C(java.lang.String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of contact groups where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching contact groups
	*/
	public static int countByUuid_C(java.lang.String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Caches the contact group in the entity cache if it is enabled.
	*
	* @param contactGroup the contact group
	*/
	public static void cacheResult(ContactGroup contactGroup) {
		getPersistence().cacheResult(contactGroup);
	}

	/**
	* Caches the contact groups in the entity cache if it is enabled.
	*
	* @param contactGroups the contact groups
	*/
	public static void cacheResult(List<ContactGroup> contactGroups) {
		getPersistence().cacheResult(contactGroups);
	}

	/**
	* Creates a new contact group with the primary key. Does not add the contact group to the database.
	*
	* @param contactGroupId the primary key for the new contact group
	* @return the new contact group
	*/
	public static ContactGroup create(long contactGroupId) {
		return getPersistence().create(contactGroupId);
	}

	/**
	* Removes the contact group with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param contactGroupId the primary key of the contact group
	* @return the contact group that was removed
	* @throws NoSuchContactGroupException if a contact group with the primary key could not be found
	*/
	public static ContactGroup remove(long contactGroupId)
		throws org.opencps.usermgt.exception.NoSuchContactGroupException {
		return getPersistence().remove(contactGroupId);
	}

	public static ContactGroup updateImpl(ContactGroup contactGroup) {
		return getPersistence().updateImpl(contactGroup);
	}

	/**
	* Returns the contact group with the primary key or throws a {@link NoSuchContactGroupException} if it could not be found.
	*
	* @param contactGroupId the primary key of the contact group
	* @return the contact group
	* @throws NoSuchContactGroupException if a contact group with the primary key could not be found
	*/
	public static ContactGroup findByPrimaryKey(long contactGroupId)
		throws org.opencps.usermgt.exception.NoSuchContactGroupException {
		return getPersistence().findByPrimaryKey(contactGroupId);
	}

	/**
	* Returns the contact group with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param contactGroupId the primary key of the contact group
	* @return the contact group, or <code>null</code> if a contact group with the primary key could not be found
	*/
	public static ContactGroup fetchByPrimaryKey(long contactGroupId) {
		return getPersistence().fetchByPrimaryKey(contactGroupId);
	}

	public static java.util.Map<java.io.Serializable, ContactGroup> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the contact groups.
	*
	* @return the contact groups
	*/
	public static List<ContactGroup> findAll() {
		return getPersistence().findAll();
	}

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
	public static List<ContactGroup> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

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
	public static List<ContactGroup> findAll(int start, int end,
		OrderByComparator<ContactGroup> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

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
	public static List<ContactGroup> findAll(int start, int end,
		OrderByComparator<ContactGroup> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the contact groups from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of contact groups.
	*
	* @return the number of contact groups
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static ContactGroupPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<ContactGroupPersistence, ContactGroupPersistence> _serviceTracker =
		ServiceTrackerFactory.open(ContactGroupPersistence.class);
}