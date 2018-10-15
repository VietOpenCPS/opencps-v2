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

package org.opencps.dossiermgt.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.opencps.dossiermgt.exception.NoSuchRegistrationException;
import org.opencps.dossiermgt.model.Registration;

/**
 * The persistence interface for the registration service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author huymq
 * @see org.opencps.dossiermgt.service.persistence.impl.RegistrationPersistenceImpl
 * @see RegistrationUtil
 * @generated
 */
@ProviderType
public interface RegistrationPersistence extends BasePersistence<Registration> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link RegistrationUtil} to access the registration persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the registrations where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching registrations
	*/
	public java.util.List<Registration> findByUuid(String uuid);

	/**
	* Returns a range of all the registrations where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of registrations
	* @param end the upper bound of the range of registrations (not inclusive)
	* @return the range of matching registrations
	*/
	public java.util.List<Registration> findByUuid(String uuid, int start,
		int end);

	/**
	* Returns an ordered range of all the registrations where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of registrations
	* @param end the upper bound of the range of registrations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching registrations
	*/
	public java.util.List<Registration> findByUuid(String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Registration> orderByComparator);

	/**
	* Returns an ordered range of all the registrations where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of registrations
	* @param end the upper bound of the range of registrations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching registrations
	*/
	public java.util.List<Registration> findByUuid(String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Registration> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first registration in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching registration
	* @throws NoSuchRegistrationException if a matching registration could not be found
	*/
	public Registration findByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Registration> orderByComparator)
		throws NoSuchRegistrationException;

	/**
	* Returns the first registration in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching registration, or <code>null</code> if a matching registration could not be found
	*/
	public Registration fetchByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Registration> orderByComparator);

	/**
	* Returns the last registration in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching registration
	* @throws NoSuchRegistrationException if a matching registration could not be found
	*/
	public Registration findByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Registration> orderByComparator)
		throws NoSuchRegistrationException;

	/**
	* Returns the last registration in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching registration, or <code>null</code> if a matching registration could not be found
	*/
	public Registration fetchByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Registration> orderByComparator);

	/**
	* Returns the registrations before and after the current registration in the ordered set where uuid = &#63;.
	*
	* @param registrationId the primary key of the current registration
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next registration
	* @throws NoSuchRegistrationException if a registration with the primary key could not be found
	*/
	public Registration[] findByUuid_PrevAndNext(long registrationId,
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Registration> orderByComparator)
		throws NoSuchRegistrationException;

	/**
	* Removes all the registrations where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(String uuid);

	/**
	* Returns the number of registrations where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching registrations
	*/
	public int countByUuid(String uuid);

	/**
	* Returns the registration where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchRegistrationException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching registration
	* @throws NoSuchRegistrationException if a matching registration could not be found
	*/
	public Registration findByUUID_G(String uuid, long groupId)
		throws NoSuchRegistrationException;

	/**
	* Returns the registration where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching registration, or <code>null</code> if a matching registration could not be found
	*/
	public Registration fetchByUUID_G(String uuid, long groupId);

	/**
	* Returns the registration where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching registration, or <code>null</code> if a matching registration could not be found
	*/
	public Registration fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the registration where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the registration that was removed
	*/
	public Registration removeByUUID_G(String uuid, long groupId)
		throws NoSuchRegistrationException;

	/**
	* Returns the number of registrations where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching registrations
	*/
	public int countByUUID_G(String uuid, long groupId);

	/**
	* Returns all the registrations where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching registrations
	*/
	public java.util.List<Registration> findByUuid_C(String uuid, long companyId);

	/**
	* Returns a range of all the registrations where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of registrations
	* @param end the upper bound of the range of registrations (not inclusive)
	* @return the range of matching registrations
	*/
	public java.util.List<Registration> findByUuid_C(String uuid,
		long companyId, int start, int end);

	/**
	* Returns an ordered range of all the registrations where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of registrations
	* @param end the upper bound of the range of registrations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching registrations
	*/
	public java.util.List<Registration> findByUuid_C(String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Registration> orderByComparator);

	/**
	* Returns an ordered range of all the registrations where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of registrations
	* @param end the upper bound of the range of registrations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching registrations
	*/
	public java.util.List<Registration> findByUuid_C(String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Registration> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first registration in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching registration
	* @throws NoSuchRegistrationException if a matching registration could not be found
	*/
	public Registration findByUuid_C_First(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Registration> orderByComparator)
		throws NoSuchRegistrationException;

	/**
	* Returns the first registration in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching registration, or <code>null</code> if a matching registration could not be found
	*/
	public Registration fetchByUuid_C_First(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Registration> orderByComparator);

	/**
	* Returns the last registration in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching registration
	* @throws NoSuchRegistrationException if a matching registration could not be found
	*/
	public Registration findByUuid_C_Last(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Registration> orderByComparator)
		throws NoSuchRegistrationException;

	/**
	* Returns the last registration in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching registration, or <code>null</code> if a matching registration could not be found
	*/
	public Registration fetchByUuid_C_Last(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Registration> orderByComparator);

	/**
	* Returns the registrations before and after the current registration in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param registrationId the primary key of the current registration
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next registration
	* @throws NoSuchRegistrationException if a registration with the primary key could not be found
	*/
	public Registration[] findByUuid_C_PrevAndNext(long registrationId,
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Registration> orderByComparator)
		throws NoSuchRegistrationException;

	/**
	* Removes all the registrations where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(String uuid, long companyId);

	/**
	* Returns the number of registrations where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching registrations
	*/
	public int countByUuid_C(String uuid, long companyId);

	/**
	* Returns all the registrations where groupId = &#63; and submitting = &#63;.
	*
	* @param groupId the group ID
	* @param submitting the submitting
	* @return the matching registrations
	*/
	public java.util.List<Registration> findByF_submitting(long groupId,
		boolean submitting);

	/**
	* Returns a range of all the registrations where groupId = &#63; and submitting = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param submitting the submitting
	* @param start the lower bound of the range of registrations
	* @param end the upper bound of the range of registrations (not inclusive)
	* @return the range of matching registrations
	*/
	public java.util.List<Registration> findByF_submitting(long groupId,
		boolean submitting, int start, int end);

	/**
	* Returns an ordered range of all the registrations where groupId = &#63; and submitting = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param submitting the submitting
	* @param start the lower bound of the range of registrations
	* @param end the upper bound of the range of registrations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching registrations
	*/
	public java.util.List<Registration> findByF_submitting(long groupId,
		boolean submitting, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Registration> orderByComparator);

	/**
	* Returns an ordered range of all the registrations where groupId = &#63; and submitting = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param submitting the submitting
	* @param start the lower bound of the range of registrations
	* @param end the upper bound of the range of registrations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching registrations
	*/
	public java.util.List<Registration> findByF_submitting(long groupId,
		boolean submitting, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Registration> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first registration in the ordered set where groupId = &#63; and submitting = &#63;.
	*
	* @param groupId the group ID
	* @param submitting the submitting
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching registration
	* @throws NoSuchRegistrationException if a matching registration could not be found
	*/
	public Registration findByF_submitting_First(long groupId,
		boolean submitting,
		com.liferay.portal.kernel.util.OrderByComparator<Registration> orderByComparator)
		throws NoSuchRegistrationException;

	/**
	* Returns the first registration in the ordered set where groupId = &#63; and submitting = &#63;.
	*
	* @param groupId the group ID
	* @param submitting the submitting
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching registration, or <code>null</code> if a matching registration could not be found
	*/
	public Registration fetchByF_submitting_First(long groupId,
		boolean submitting,
		com.liferay.portal.kernel.util.OrderByComparator<Registration> orderByComparator);

	/**
	* Returns the last registration in the ordered set where groupId = &#63; and submitting = &#63;.
	*
	* @param groupId the group ID
	* @param submitting the submitting
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching registration
	* @throws NoSuchRegistrationException if a matching registration could not be found
	*/
	public Registration findByF_submitting_Last(long groupId,
		boolean submitting,
		com.liferay.portal.kernel.util.OrderByComparator<Registration> orderByComparator)
		throws NoSuchRegistrationException;

	/**
	* Returns the last registration in the ordered set where groupId = &#63; and submitting = &#63;.
	*
	* @param groupId the group ID
	* @param submitting the submitting
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching registration, or <code>null</code> if a matching registration could not be found
	*/
	public Registration fetchByF_submitting_Last(long groupId,
		boolean submitting,
		com.liferay.portal.kernel.util.OrderByComparator<Registration> orderByComparator);

	/**
	* Returns the registrations before and after the current registration in the ordered set where groupId = &#63; and submitting = &#63;.
	*
	* @param registrationId the primary key of the current registration
	* @param groupId the group ID
	* @param submitting the submitting
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next registration
	* @throws NoSuchRegistrationException if a registration with the primary key could not be found
	*/
	public Registration[] findByF_submitting_PrevAndNext(long registrationId,
		long groupId, boolean submitting,
		com.liferay.portal.kernel.util.OrderByComparator<Registration> orderByComparator)
		throws NoSuchRegistrationException;

	/**
	* Removes all the registrations where groupId = &#63; and submitting = &#63; from the database.
	*
	* @param groupId the group ID
	* @param submitting the submitting
	*/
	public void removeByF_submitting(long groupId, boolean submitting);

	/**
	* Returns the number of registrations where groupId = &#63; and submitting = &#63;.
	*
	* @param groupId the group ID
	* @param submitting the submitting
	* @return the number of matching registrations
	*/
	public int countByF_submitting(long groupId, boolean submitting);

	/**
	* Returns all the registrations where groupId = &#63; and userId = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @return the matching registrations
	*/
	public java.util.List<Registration> findByGID_UID(long groupId, long userId);

	/**
	* Returns a range of all the registrations where groupId = &#63; and userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param start the lower bound of the range of registrations
	* @param end the upper bound of the range of registrations (not inclusive)
	* @return the range of matching registrations
	*/
	public java.util.List<Registration> findByGID_UID(long groupId,
		long userId, int start, int end);

	/**
	* Returns an ordered range of all the registrations where groupId = &#63; and userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param start the lower bound of the range of registrations
	* @param end the upper bound of the range of registrations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching registrations
	*/
	public java.util.List<Registration> findByGID_UID(long groupId,
		long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Registration> orderByComparator);

	/**
	* Returns an ordered range of all the registrations where groupId = &#63; and userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param start the lower bound of the range of registrations
	* @param end the upper bound of the range of registrations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching registrations
	*/
	public java.util.List<Registration> findByGID_UID(long groupId,
		long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Registration> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first registration in the ordered set where groupId = &#63; and userId = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching registration
	* @throws NoSuchRegistrationException if a matching registration could not be found
	*/
	public Registration findByGID_UID_First(long groupId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator<Registration> orderByComparator)
		throws NoSuchRegistrationException;

	/**
	* Returns the first registration in the ordered set where groupId = &#63; and userId = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching registration, or <code>null</code> if a matching registration could not be found
	*/
	public Registration fetchByGID_UID_First(long groupId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator<Registration> orderByComparator);

	/**
	* Returns the last registration in the ordered set where groupId = &#63; and userId = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching registration
	* @throws NoSuchRegistrationException if a matching registration could not be found
	*/
	public Registration findByGID_UID_Last(long groupId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator<Registration> orderByComparator)
		throws NoSuchRegistrationException;

	/**
	* Returns the last registration in the ordered set where groupId = &#63; and userId = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching registration, or <code>null</code> if a matching registration could not be found
	*/
	public Registration fetchByGID_UID_Last(long groupId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator<Registration> orderByComparator);

	/**
	* Returns the registrations before and after the current registration in the ordered set where groupId = &#63; and userId = &#63;.
	*
	* @param registrationId the primary key of the current registration
	* @param groupId the group ID
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next registration
	* @throws NoSuchRegistrationException if a registration with the primary key could not be found
	*/
	public Registration[] findByGID_UID_PrevAndNext(long registrationId,
		long groupId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator<Registration> orderByComparator)
		throws NoSuchRegistrationException;

	/**
	* Removes all the registrations where groupId = &#63; and userId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param userId the user ID
	*/
	public void removeByGID_UID(long groupId, long userId);

	/**
	* Returns the number of registrations where groupId = &#63; and userId = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @return the number of matching registrations
	*/
	public int countByGID_UID(long groupId, long userId);

	/**
	* Returns the registration where groupId = &#63; and registrationId = &#63; or throws a {@link NoSuchRegistrationException} if it could not be found.
	*
	* @param groupId the group ID
	* @param registrationId the registration ID
	* @return the matching registration
	* @throws NoSuchRegistrationException if a matching registration could not be found
	*/
	public Registration findByG_REGID(long groupId, long registrationId)
		throws NoSuchRegistrationException;

	/**
	* Returns the registration where groupId = &#63; and registrationId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param registrationId the registration ID
	* @return the matching registration, or <code>null</code> if a matching registration could not be found
	*/
	public Registration fetchByG_REGID(long groupId, long registrationId);

	/**
	* Returns the registration where groupId = &#63; and registrationId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param registrationId the registration ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching registration, or <code>null</code> if a matching registration could not be found
	*/
	public Registration fetchByG_REGID(long groupId, long registrationId,
		boolean retrieveFromCache);

	/**
	* Removes the registration where groupId = &#63; and registrationId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param registrationId the registration ID
	* @return the registration that was removed
	*/
	public Registration removeByG_REGID(long groupId, long registrationId)
		throws NoSuchRegistrationException;

	/**
	* Returns the number of registrations where groupId = &#63; and registrationId = &#63;.
	*
	* @param groupId the group ID
	* @param registrationId the registration ID
	* @return the number of matching registrations
	*/
	public int countByG_REGID(long groupId, long registrationId);

	/**
	* Returns all the registrations where groupId = &#63; and userId = &#63; and submitting = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param submitting the submitting
	* @return the matching registrations
	*/
	public java.util.List<Registration> findByF_USERID_SUBMITTING(
		long groupId, long userId, boolean submitting);

	/**
	* Returns a range of all the registrations where groupId = &#63; and userId = &#63; and submitting = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param submitting the submitting
	* @param start the lower bound of the range of registrations
	* @param end the upper bound of the range of registrations (not inclusive)
	* @return the range of matching registrations
	*/
	public java.util.List<Registration> findByF_USERID_SUBMITTING(
		long groupId, long userId, boolean submitting, int start, int end);

	/**
	* Returns an ordered range of all the registrations where groupId = &#63; and userId = &#63; and submitting = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param submitting the submitting
	* @param start the lower bound of the range of registrations
	* @param end the upper bound of the range of registrations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching registrations
	*/
	public java.util.List<Registration> findByF_USERID_SUBMITTING(
		long groupId, long userId, boolean submitting, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Registration> orderByComparator);

	/**
	* Returns an ordered range of all the registrations where groupId = &#63; and userId = &#63; and submitting = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param submitting the submitting
	* @param start the lower bound of the range of registrations
	* @param end the upper bound of the range of registrations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching registrations
	*/
	public java.util.List<Registration> findByF_USERID_SUBMITTING(
		long groupId, long userId, boolean submitting, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Registration> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first registration in the ordered set where groupId = &#63; and userId = &#63; and submitting = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param submitting the submitting
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching registration
	* @throws NoSuchRegistrationException if a matching registration could not be found
	*/
	public Registration findByF_USERID_SUBMITTING_First(long groupId,
		long userId, boolean submitting,
		com.liferay.portal.kernel.util.OrderByComparator<Registration> orderByComparator)
		throws NoSuchRegistrationException;

	/**
	* Returns the first registration in the ordered set where groupId = &#63; and userId = &#63; and submitting = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param submitting the submitting
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching registration, or <code>null</code> if a matching registration could not be found
	*/
	public Registration fetchByF_USERID_SUBMITTING_First(long groupId,
		long userId, boolean submitting,
		com.liferay.portal.kernel.util.OrderByComparator<Registration> orderByComparator);

	/**
	* Returns the last registration in the ordered set where groupId = &#63; and userId = &#63; and submitting = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param submitting the submitting
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching registration
	* @throws NoSuchRegistrationException if a matching registration could not be found
	*/
	public Registration findByF_USERID_SUBMITTING_Last(long groupId,
		long userId, boolean submitting,
		com.liferay.portal.kernel.util.OrderByComparator<Registration> orderByComparator)
		throws NoSuchRegistrationException;

	/**
	* Returns the last registration in the ordered set where groupId = &#63; and userId = &#63; and submitting = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param submitting the submitting
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching registration, or <code>null</code> if a matching registration could not be found
	*/
	public Registration fetchByF_USERID_SUBMITTING_Last(long groupId,
		long userId, boolean submitting,
		com.liferay.portal.kernel.util.OrderByComparator<Registration> orderByComparator);

	/**
	* Returns the registrations before and after the current registration in the ordered set where groupId = &#63; and userId = &#63; and submitting = &#63;.
	*
	* @param registrationId the primary key of the current registration
	* @param groupId the group ID
	* @param userId the user ID
	* @param submitting the submitting
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next registration
	* @throws NoSuchRegistrationException if a registration with the primary key could not be found
	*/
	public Registration[] findByF_USERID_SUBMITTING_PrevAndNext(
		long registrationId, long groupId, long userId, boolean submitting,
		com.liferay.portal.kernel.util.OrderByComparator<Registration> orderByComparator)
		throws NoSuchRegistrationException;

	/**
	* Removes all the registrations where groupId = &#63; and userId = &#63; and submitting = &#63; from the database.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param submitting the submitting
	*/
	public void removeByF_USERID_SUBMITTING(long groupId, long userId,
		boolean submitting);

	/**
	* Returns the number of registrations where groupId = &#63; and userId = &#63; and submitting = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param submitting the submitting
	* @return the number of matching registrations
	*/
	public int countByF_USERID_SUBMITTING(long groupId, long userId,
		boolean submitting);

	/**
	* Returns all the registrations where groupId = &#63; and applicantIdNo = &#63; and govAgencyCode = &#63; and registrationState = &#63;.
	*
	* @param groupId the group ID
	* @param applicantIdNo the applicant ID no
	* @param govAgencyCode the gov agency code
	* @param registrationState the registration state
	* @return the matching registrations
	*/
	public java.util.List<Registration> findByG_APPNO_GOVCODE(long groupId,
		String applicantIdNo, String govAgencyCode, int registrationState);

	/**
	* Returns a range of all the registrations where groupId = &#63; and applicantIdNo = &#63; and govAgencyCode = &#63; and registrationState = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param applicantIdNo the applicant ID no
	* @param govAgencyCode the gov agency code
	* @param registrationState the registration state
	* @param start the lower bound of the range of registrations
	* @param end the upper bound of the range of registrations (not inclusive)
	* @return the range of matching registrations
	*/
	public java.util.List<Registration> findByG_APPNO_GOVCODE(long groupId,
		String applicantIdNo, String govAgencyCode, int registrationState,
		int start, int end);

	/**
	* Returns an ordered range of all the registrations where groupId = &#63; and applicantIdNo = &#63; and govAgencyCode = &#63; and registrationState = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param applicantIdNo the applicant ID no
	* @param govAgencyCode the gov agency code
	* @param registrationState the registration state
	* @param start the lower bound of the range of registrations
	* @param end the upper bound of the range of registrations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching registrations
	*/
	public java.util.List<Registration> findByG_APPNO_GOVCODE(long groupId,
		String applicantIdNo, String govAgencyCode, int registrationState,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Registration> orderByComparator);

	/**
	* Returns an ordered range of all the registrations where groupId = &#63; and applicantIdNo = &#63; and govAgencyCode = &#63; and registrationState = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param applicantIdNo the applicant ID no
	* @param govAgencyCode the gov agency code
	* @param registrationState the registration state
	* @param start the lower bound of the range of registrations
	* @param end the upper bound of the range of registrations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching registrations
	*/
	public java.util.List<Registration> findByG_APPNO_GOVCODE(long groupId,
		String applicantIdNo, String govAgencyCode, int registrationState,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Registration> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first registration in the ordered set where groupId = &#63; and applicantIdNo = &#63; and govAgencyCode = &#63; and registrationState = &#63;.
	*
	* @param groupId the group ID
	* @param applicantIdNo the applicant ID no
	* @param govAgencyCode the gov agency code
	* @param registrationState the registration state
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching registration
	* @throws NoSuchRegistrationException if a matching registration could not be found
	*/
	public Registration findByG_APPNO_GOVCODE_First(long groupId,
		String applicantIdNo, String govAgencyCode, int registrationState,
		com.liferay.portal.kernel.util.OrderByComparator<Registration> orderByComparator)
		throws NoSuchRegistrationException;

	/**
	* Returns the first registration in the ordered set where groupId = &#63; and applicantIdNo = &#63; and govAgencyCode = &#63; and registrationState = &#63;.
	*
	* @param groupId the group ID
	* @param applicantIdNo the applicant ID no
	* @param govAgencyCode the gov agency code
	* @param registrationState the registration state
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching registration, or <code>null</code> if a matching registration could not be found
	*/
	public Registration fetchByG_APPNO_GOVCODE_First(long groupId,
		String applicantIdNo, String govAgencyCode, int registrationState,
		com.liferay.portal.kernel.util.OrderByComparator<Registration> orderByComparator);

	/**
	* Returns the last registration in the ordered set where groupId = &#63; and applicantIdNo = &#63; and govAgencyCode = &#63; and registrationState = &#63;.
	*
	* @param groupId the group ID
	* @param applicantIdNo the applicant ID no
	* @param govAgencyCode the gov agency code
	* @param registrationState the registration state
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching registration
	* @throws NoSuchRegistrationException if a matching registration could not be found
	*/
	public Registration findByG_APPNO_GOVCODE_Last(long groupId,
		String applicantIdNo, String govAgencyCode, int registrationState,
		com.liferay.portal.kernel.util.OrderByComparator<Registration> orderByComparator)
		throws NoSuchRegistrationException;

	/**
	* Returns the last registration in the ordered set where groupId = &#63; and applicantIdNo = &#63; and govAgencyCode = &#63; and registrationState = &#63;.
	*
	* @param groupId the group ID
	* @param applicantIdNo the applicant ID no
	* @param govAgencyCode the gov agency code
	* @param registrationState the registration state
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching registration, or <code>null</code> if a matching registration could not be found
	*/
	public Registration fetchByG_APPNO_GOVCODE_Last(long groupId,
		String applicantIdNo, String govAgencyCode, int registrationState,
		com.liferay.portal.kernel.util.OrderByComparator<Registration> orderByComparator);

	/**
	* Returns the registrations before and after the current registration in the ordered set where groupId = &#63; and applicantIdNo = &#63; and govAgencyCode = &#63; and registrationState = &#63;.
	*
	* @param registrationId the primary key of the current registration
	* @param groupId the group ID
	* @param applicantIdNo the applicant ID no
	* @param govAgencyCode the gov agency code
	* @param registrationState the registration state
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next registration
	* @throws NoSuchRegistrationException if a registration with the primary key could not be found
	*/
	public Registration[] findByG_APPNO_GOVCODE_PrevAndNext(
		long registrationId, long groupId, String applicantIdNo,
		String govAgencyCode, int registrationState,
		com.liferay.portal.kernel.util.OrderByComparator<Registration> orderByComparator)
		throws NoSuchRegistrationException;

	/**
	* Removes all the registrations where groupId = &#63; and applicantIdNo = &#63; and govAgencyCode = &#63; and registrationState = &#63; from the database.
	*
	* @param groupId the group ID
	* @param applicantIdNo the applicant ID no
	* @param govAgencyCode the gov agency code
	* @param registrationState the registration state
	*/
	public void removeByG_APPNO_GOVCODE(long groupId, String applicantIdNo,
		String govAgencyCode, int registrationState);

	/**
	* Returns the number of registrations where groupId = &#63; and applicantIdNo = &#63; and govAgencyCode = &#63; and registrationState = &#63;.
	*
	* @param groupId the group ID
	* @param applicantIdNo the applicant ID no
	* @param govAgencyCode the gov agency code
	* @param registrationState the registration state
	* @return the number of matching registrations
	*/
	public int countByG_APPNO_GOVCODE(long groupId, String applicantIdNo,
		String govAgencyCode, int registrationState);

	/**
	* Returns all the registrations where groupId = &#63; and userId = &#63; and registrationState = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param registrationState the registration state
	* @return the matching registrations
	*/
	public java.util.List<Registration> findByG_USER_STATE(long groupId,
		long userId, int registrationState);

	/**
	* Returns a range of all the registrations where groupId = &#63; and userId = &#63; and registrationState = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param registrationState the registration state
	* @param start the lower bound of the range of registrations
	* @param end the upper bound of the range of registrations (not inclusive)
	* @return the range of matching registrations
	*/
	public java.util.List<Registration> findByG_USER_STATE(long groupId,
		long userId, int registrationState, int start, int end);

	/**
	* Returns an ordered range of all the registrations where groupId = &#63; and userId = &#63; and registrationState = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param registrationState the registration state
	* @param start the lower bound of the range of registrations
	* @param end the upper bound of the range of registrations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching registrations
	*/
	public java.util.List<Registration> findByG_USER_STATE(long groupId,
		long userId, int registrationState, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Registration> orderByComparator);

	/**
	* Returns an ordered range of all the registrations where groupId = &#63; and userId = &#63; and registrationState = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param registrationState the registration state
	* @param start the lower bound of the range of registrations
	* @param end the upper bound of the range of registrations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching registrations
	*/
	public java.util.List<Registration> findByG_USER_STATE(long groupId,
		long userId, int registrationState, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Registration> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first registration in the ordered set where groupId = &#63; and userId = &#63; and registrationState = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param registrationState the registration state
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching registration
	* @throws NoSuchRegistrationException if a matching registration could not be found
	*/
	public Registration findByG_USER_STATE_First(long groupId, long userId,
		int registrationState,
		com.liferay.portal.kernel.util.OrderByComparator<Registration> orderByComparator)
		throws NoSuchRegistrationException;

	/**
	* Returns the first registration in the ordered set where groupId = &#63; and userId = &#63; and registrationState = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param registrationState the registration state
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching registration, or <code>null</code> if a matching registration could not be found
	*/
	public Registration fetchByG_USER_STATE_First(long groupId, long userId,
		int registrationState,
		com.liferay.portal.kernel.util.OrderByComparator<Registration> orderByComparator);

	/**
	* Returns the last registration in the ordered set where groupId = &#63; and userId = &#63; and registrationState = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param registrationState the registration state
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching registration
	* @throws NoSuchRegistrationException if a matching registration could not be found
	*/
	public Registration findByG_USER_STATE_Last(long groupId, long userId,
		int registrationState,
		com.liferay.portal.kernel.util.OrderByComparator<Registration> orderByComparator)
		throws NoSuchRegistrationException;

	/**
	* Returns the last registration in the ordered set where groupId = &#63; and userId = &#63; and registrationState = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param registrationState the registration state
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching registration, or <code>null</code> if a matching registration could not be found
	*/
	public Registration fetchByG_USER_STATE_Last(long groupId, long userId,
		int registrationState,
		com.liferay.portal.kernel.util.OrderByComparator<Registration> orderByComparator);

	/**
	* Returns the registrations before and after the current registration in the ordered set where groupId = &#63; and userId = &#63; and registrationState = &#63;.
	*
	* @param registrationId the primary key of the current registration
	* @param groupId the group ID
	* @param userId the user ID
	* @param registrationState the registration state
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next registration
	* @throws NoSuchRegistrationException if a registration with the primary key could not be found
	*/
	public Registration[] findByG_USER_STATE_PrevAndNext(long registrationId,
		long groupId, long userId, int registrationState,
		com.liferay.portal.kernel.util.OrderByComparator<Registration> orderByComparator)
		throws NoSuchRegistrationException;

	/**
	* Removes all the registrations where groupId = &#63; and userId = &#63; and registrationState = &#63; from the database.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param registrationState the registration state
	*/
	public void removeByG_USER_STATE(long groupId, long userId,
		int registrationState);

	/**
	* Returns the number of registrations where groupId = &#63; and userId = &#63; and registrationState = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param registrationState the registration state
	* @return the number of matching registrations
	*/
	public int countByG_USER_STATE(long groupId, long userId,
		int registrationState);

	/**
	* Returns the registration where applicantIdNo = &#63; or throws a {@link NoSuchRegistrationException} if it could not be found.
	*
	* @param applicantIdNo the applicant ID no
	* @return the matching registration
	* @throws NoSuchRegistrationException if a matching registration could not be found
	*/
	public Registration findByREG_APPNO(String applicantIdNo)
		throws NoSuchRegistrationException;

	/**
	* Returns the registration where applicantIdNo = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param applicantIdNo the applicant ID no
	* @return the matching registration, or <code>null</code> if a matching registration could not be found
	*/
	public Registration fetchByREG_APPNO(String applicantIdNo);

	/**
	* Returns the registration where applicantIdNo = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param applicantIdNo the applicant ID no
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching registration, or <code>null</code> if a matching registration could not be found
	*/
	public Registration fetchByREG_APPNO(String applicantIdNo,
		boolean retrieveFromCache);

	/**
	* Removes the registration where applicantIdNo = &#63; from the database.
	*
	* @param applicantIdNo the applicant ID no
	* @return the registration that was removed
	*/
	public Registration removeByREG_APPNO(String applicantIdNo)
		throws NoSuchRegistrationException;

	/**
	* Returns the number of registrations where applicantIdNo = &#63;.
	*
	* @param applicantIdNo the applicant ID no
	* @return the number of matching registrations
	*/
	public int countByREG_APPNO(String applicantIdNo);

	/**
	* Caches the registration in the entity cache if it is enabled.
	*
	* @param registration the registration
	*/
	public void cacheResult(Registration registration);

	/**
	* Caches the registrations in the entity cache if it is enabled.
	*
	* @param registrations the registrations
	*/
	public void cacheResult(java.util.List<Registration> registrations);

	/**
	* Creates a new registration with the primary key. Does not add the registration to the database.
	*
	* @param registrationId the primary key for the new registration
	* @return the new registration
	*/
	public Registration create(long registrationId);

	/**
	* Removes the registration with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param registrationId the primary key of the registration
	* @return the registration that was removed
	* @throws NoSuchRegistrationException if a registration with the primary key could not be found
	*/
	public Registration remove(long registrationId)
		throws NoSuchRegistrationException;

	public Registration updateImpl(Registration registration);

	/**
	* Returns the registration with the primary key or throws a {@link NoSuchRegistrationException} if it could not be found.
	*
	* @param registrationId the primary key of the registration
	* @return the registration
	* @throws NoSuchRegistrationException if a registration with the primary key could not be found
	*/
	public Registration findByPrimaryKey(long registrationId)
		throws NoSuchRegistrationException;

	/**
	* Returns the registration with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param registrationId the primary key of the registration
	* @return the registration, or <code>null</code> if a registration with the primary key could not be found
	*/
	public Registration fetchByPrimaryKey(long registrationId);

	@Override
	public java.util.Map<java.io.Serializable, Registration> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the registrations.
	*
	* @return the registrations
	*/
	public java.util.List<Registration> findAll();

	/**
	* Returns a range of all the registrations.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of registrations
	* @param end the upper bound of the range of registrations (not inclusive)
	* @return the range of registrations
	*/
	public java.util.List<Registration> findAll(int start, int end);

	/**
	* Returns an ordered range of all the registrations.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of registrations
	* @param end the upper bound of the range of registrations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of registrations
	*/
	public java.util.List<Registration> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Registration> orderByComparator);

	/**
	* Returns an ordered range of all the registrations.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of registrations
	* @param end the upper bound of the range of registrations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of registrations
	*/
	public java.util.List<Registration> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Registration> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the registrations from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of registrations.
	*
	* @return the number of registrations
	*/
	public int countAll();

	@Override
	public java.util.Set<String> getBadColumnNames();
}