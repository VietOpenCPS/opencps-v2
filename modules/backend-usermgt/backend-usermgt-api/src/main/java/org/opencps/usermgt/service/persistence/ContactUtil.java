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

import org.opencps.usermgt.model.Contact;
import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the contact service. This utility wraps {@link org.mobilink.backend.usermgt.service.persistence.impl.ContactPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Binhth
 * @see ContactPersistence
 * @see org.mobilink.backend.usermgt.service.persistence.impl.ContactPersistenceImpl
 * @generated
 */
@ProviderType
public class ContactUtil {
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
	public static void clearCache(Contact contact) {
		getPersistence().clearCache(contact);
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
	public static List<Contact> findWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Contact> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Contact> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<Contact> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static Contact update(Contact contact) {
		return getPersistence().update(contact);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static Contact update(Contact contact, ServiceContext serviceContext) {
		return getPersistence().update(contact, serviceContext);
	}

	/**
	* Returns all the contacts where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching contacts
	*/
	public static List<Contact> findByUuid(java.lang.String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the contacts where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContactModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of contacts
	* @param end the upper bound of the range of contacts (not inclusive)
	* @return the range of matching contacts
	*/
	public static List<Contact> findByUuid(java.lang.String uuid, int start,
		int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the contacts where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContactModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of contacts
	* @param end the upper bound of the range of contacts (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching contacts
	*/
	public static List<Contact> findByUuid(java.lang.String uuid, int start,
		int end, OrderByComparator<Contact> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the contacts where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContactModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of contacts
	* @param end the upper bound of the range of contacts (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching contacts
	*/
	public static List<Contact> findByUuid(java.lang.String uuid, int start,
		int end, OrderByComparator<Contact> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first contact in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching contact
	* @throws NoSuchContactException if a matching contact could not be found
	*/
	public static Contact findByUuid_First(java.lang.String uuid,
		OrderByComparator<Contact> orderByComparator)
		throws org.opencps.usermgt.exception.NoSuchContactException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first contact in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching contact, or <code>null</code> if a matching contact could not be found
	*/
	public static Contact fetchByUuid_First(java.lang.String uuid,
		OrderByComparator<Contact> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last contact in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching contact
	* @throws NoSuchContactException if a matching contact could not be found
	*/
	public static Contact findByUuid_Last(java.lang.String uuid,
		OrderByComparator<Contact> orderByComparator)
		throws org.opencps.usermgt.exception.NoSuchContactException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last contact in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching contact, or <code>null</code> if a matching contact could not be found
	*/
	public static Contact fetchByUuid_Last(java.lang.String uuid,
		OrderByComparator<Contact> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the contacts before and after the current contact in the ordered set where uuid = &#63;.
	*
	* @param contactId the primary key of the current contact
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next contact
	* @throws NoSuchContactException if a contact with the primary key could not be found
	*/
	public static Contact[] findByUuid_PrevAndNext(long contactId,
		java.lang.String uuid, OrderByComparator<Contact> orderByComparator)
		throws org.opencps.usermgt.exception.NoSuchContactException {
		return getPersistence()
				   .findByUuid_PrevAndNext(contactId, uuid, orderByComparator);
	}

	/**
	* Removes all the contacts where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(java.lang.String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of contacts where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching contacts
	*/
	public static int countByUuid(java.lang.String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the contact where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchContactException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching contact
	* @throws NoSuchContactException if a matching contact could not be found
	*/
	public static Contact findByUUID_G(java.lang.String uuid, long groupId)
		throws org.opencps.usermgt.exception.NoSuchContactException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the contact where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching contact, or <code>null</code> if a matching contact could not be found
	*/
	public static Contact fetchByUUID_G(java.lang.String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the contact where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching contact, or <code>null</code> if a matching contact could not be found
	*/
	public static Contact fetchByUUID_G(java.lang.String uuid, long groupId,
		boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the contact where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the contact that was removed
	*/
	public static Contact removeByUUID_G(java.lang.String uuid, long groupId)
		throws org.opencps.usermgt.exception.NoSuchContactException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of contacts where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching contacts
	*/
	public static int countByUUID_G(java.lang.String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the contacts where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching contacts
	*/
	public static List<Contact> findByUuid_C(java.lang.String uuid,
		long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	* Returns a range of all the contacts where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContactModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of contacts
	* @param end the upper bound of the range of contacts (not inclusive)
	* @return the range of matching contacts
	*/
	public static List<Contact> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end) {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	* Returns an ordered range of all the contacts where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContactModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of contacts
	* @param end the upper bound of the range of contacts (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching contacts
	*/
	public static List<Contact> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		OrderByComparator<Contact> orderByComparator) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the contacts where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContactModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of contacts
	* @param end the upper bound of the range of contacts (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching contacts
	*/
	public static List<Contact> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		OrderByComparator<Contact> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first contact in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching contact
	* @throws NoSuchContactException if a matching contact could not be found
	*/
	public static Contact findByUuid_C_First(java.lang.String uuid,
		long companyId, OrderByComparator<Contact> orderByComparator)
		throws org.opencps.usermgt.exception.NoSuchContactException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first contact in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching contact, or <code>null</code> if a matching contact could not be found
	*/
	public static Contact fetchByUuid_C_First(java.lang.String uuid,
		long companyId, OrderByComparator<Contact> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last contact in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching contact
	* @throws NoSuchContactException if a matching contact could not be found
	*/
	public static Contact findByUuid_C_Last(java.lang.String uuid,
		long companyId, OrderByComparator<Contact> orderByComparator)
		throws org.opencps.usermgt.exception.NoSuchContactException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last contact in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching contact, or <code>null</code> if a matching contact could not be found
	*/
	public static Contact fetchByUuid_C_Last(java.lang.String uuid,
		long companyId, OrderByComparator<Contact> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the contacts before and after the current contact in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param contactId the primary key of the current contact
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next contact
	* @throws NoSuchContactException if a contact with the primary key could not be found
	*/
	public static Contact[] findByUuid_C_PrevAndNext(long contactId,
		java.lang.String uuid, long companyId,
		OrderByComparator<Contact> orderByComparator)
		throws org.opencps.usermgt.exception.NoSuchContactException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(contactId, uuid, companyId,
			orderByComparator);
	}

	/**
	* Removes all the contacts where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public static void removeByUuid_C(java.lang.String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of contacts where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching contacts
	*/
	public static int countByUuid_C(java.lang.String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Returns the contact where userMappingId = &#63; or throws a {@link NoSuchContactException} if it could not be found.
	*
	* @param userMappingId the user mapping ID
	* @return the matching contact
	* @throws NoSuchContactException if a matching contact could not be found
	*/
	public static Contact findByF_userMappingId(long userMappingId)
		throws org.opencps.usermgt.exception.NoSuchContactException {
		return getPersistence().findByF_userMappingId(userMappingId);
	}

	/**
	* Returns the contact where userMappingId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param userMappingId the user mapping ID
	* @return the matching contact, or <code>null</code> if a matching contact could not be found
	*/
	public static Contact fetchByF_userMappingId(long userMappingId) {
		return getPersistence().fetchByF_userMappingId(userMappingId);
	}

	/**
	* Returns the contact where userMappingId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param userMappingId the user mapping ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching contact, or <code>null</code> if a matching contact could not be found
	*/
	public static Contact fetchByF_userMappingId(long userMappingId,
		boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByF_userMappingId(userMappingId, retrieveFromCache);
	}

	/**
	* Removes the contact where userMappingId = &#63; from the database.
	*
	* @param userMappingId the user mapping ID
	* @return the contact that was removed
	*/
	public static Contact removeByF_userMappingId(long userMappingId)
		throws org.opencps.usermgt.exception.NoSuchContactException {
		return getPersistence().removeByF_userMappingId(userMappingId);
	}

	/**
	* Returns the number of contacts where userMappingId = &#63;.
	*
	* @param userMappingId the user mapping ID
	* @return the number of matching contacts
	*/
	public static int countByF_userMappingId(long userMappingId) {
		return getPersistence().countByF_userMappingId(userMappingId);
	}

	/**
	* Caches the contact in the entity cache if it is enabled.
	*
	* @param contact the contact
	*/
	public static void cacheResult(Contact contact) {
		getPersistence().cacheResult(contact);
	}

	/**
	* Caches the contacts in the entity cache if it is enabled.
	*
	* @param contacts the contacts
	*/
	public static void cacheResult(List<Contact> contacts) {
		getPersistence().cacheResult(contacts);
	}

	/**
	* Creates a new contact with the primary key. Does not add the contact to the database.
	*
	* @param contactId the primary key for the new contact
	* @return the new contact
	*/
	public static Contact create(long contactId) {
		return getPersistence().create(contactId);
	}

	/**
	* Removes the contact with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param contactId the primary key of the contact
	* @return the contact that was removed
	* @throws NoSuchContactException if a contact with the primary key could not be found
	*/
	public static Contact remove(long contactId)
		throws org.opencps.usermgt.exception.NoSuchContactException {
		return getPersistence().remove(contactId);
	}

	public static Contact updateImpl(Contact contact) {
		return getPersistence().updateImpl(contact);
	}

	/**
	* Returns the contact with the primary key or throws a {@link NoSuchContactException} if it could not be found.
	*
	* @param contactId the primary key of the contact
	* @return the contact
	* @throws NoSuchContactException if a contact with the primary key could not be found
	*/
	public static Contact findByPrimaryKey(long contactId)
		throws org.opencps.usermgt.exception.NoSuchContactException {
		return getPersistence().findByPrimaryKey(contactId);
	}

	/**
	* Returns the contact with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param contactId the primary key of the contact
	* @return the contact, or <code>null</code> if a contact with the primary key could not be found
	*/
	public static Contact fetchByPrimaryKey(long contactId) {
		return getPersistence().fetchByPrimaryKey(contactId);
	}

	public static java.util.Map<java.io.Serializable, Contact> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the contacts.
	*
	* @return the contacts
	*/
	public static List<Contact> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the contacts.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContactModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of contacts
	* @param end the upper bound of the range of contacts (not inclusive)
	* @return the range of contacts
	*/
	public static List<Contact> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the contacts.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContactModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of contacts
	* @param end the upper bound of the range of contacts (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of contacts
	*/
	public static List<Contact> findAll(int start, int end,
		OrderByComparator<Contact> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the contacts.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContactModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of contacts
	* @param end the upper bound of the range of contacts (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of contacts
	*/
	public static List<Contact> findAll(int start, int end,
		OrderByComparator<Contact> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the contacts from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of contacts.
	*
	* @return the number of contacts
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static ContactPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<ContactPersistence, ContactPersistence> _serviceTracker =
		ServiceTrackerFactory.open(ContactPersistence.class);
}