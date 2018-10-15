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

import org.opencps.dossiermgt.exception.NoSuchRegistrationFormException;
import org.opencps.dossiermgt.model.RegistrationForm;

/**
 * The persistence interface for the registration form service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author huymq
 * @see org.opencps.dossiermgt.service.persistence.impl.RegistrationFormPersistenceImpl
 * @see RegistrationFormUtil
 * @generated
 */
@ProviderType
public interface RegistrationFormPersistence extends BasePersistence<RegistrationForm> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link RegistrationFormUtil} to access the registration form persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the registration forms where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching registration forms
	*/
	public java.util.List<RegistrationForm> findByUuid(String uuid);

	/**
	* Returns a range of all the registration forms where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationFormModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of registration forms
	* @param end the upper bound of the range of registration forms (not inclusive)
	* @return the range of matching registration forms
	*/
	public java.util.List<RegistrationForm> findByUuid(String uuid, int start,
		int end);

	/**
	* Returns an ordered range of all the registration forms where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationFormModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of registration forms
	* @param end the upper bound of the range of registration forms (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching registration forms
	*/
	public java.util.List<RegistrationForm> findByUuid(String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<RegistrationForm> orderByComparator);

	/**
	* Returns an ordered range of all the registration forms where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationFormModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of registration forms
	* @param end the upper bound of the range of registration forms (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching registration forms
	*/
	public java.util.List<RegistrationForm> findByUuid(String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<RegistrationForm> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first registration form in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching registration form
	* @throws NoSuchRegistrationFormException if a matching registration form could not be found
	*/
	public RegistrationForm findByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<RegistrationForm> orderByComparator)
		throws NoSuchRegistrationFormException;

	/**
	* Returns the first registration form in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching registration form, or <code>null</code> if a matching registration form could not be found
	*/
	public RegistrationForm fetchByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<RegistrationForm> orderByComparator);

	/**
	* Returns the last registration form in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching registration form
	* @throws NoSuchRegistrationFormException if a matching registration form could not be found
	*/
	public RegistrationForm findByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<RegistrationForm> orderByComparator)
		throws NoSuchRegistrationFormException;

	/**
	* Returns the last registration form in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching registration form, or <code>null</code> if a matching registration form could not be found
	*/
	public RegistrationForm fetchByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<RegistrationForm> orderByComparator);

	/**
	* Returns the registration forms before and after the current registration form in the ordered set where uuid = &#63;.
	*
	* @param registrationFormId the primary key of the current registration form
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next registration form
	* @throws NoSuchRegistrationFormException if a registration form with the primary key could not be found
	*/
	public RegistrationForm[] findByUuid_PrevAndNext(long registrationFormId,
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<RegistrationForm> orderByComparator)
		throws NoSuchRegistrationFormException;

	/**
	* Removes all the registration forms where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(String uuid);

	/**
	* Returns the number of registration forms where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching registration forms
	*/
	public int countByUuid(String uuid);

	/**
	* Returns the registration form where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchRegistrationFormException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching registration form
	* @throws NoSuchRegistrationFormException if a matching registration form could not be found
	*/
	public RegistrationForm findByUUID_G(String uuid, long groupId)
		throws NoSuchRegistrationFormException;

	/**
	* Returns the registration form where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching registration form, or <code>null</code> if a matching registration form could not be found
	*/
	public RegistrationForm fetchByUUID_G(String uuid, long groupId);

	/**
	* Returns the registration form where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching registration form, or <code>null</code> if a matching registration form could not be found
	*/
	public RegistrationForm fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the registration form where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the registration form that was removed
	*/
	public RegistrationForm removeByUUID_G(String uuid, long groupId)
		throws NoSuchRegistrationFormException;

	/**
	* Returns the number of registration forms where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching registration forms
	*/
	public int countByUUID_G(String uuid, long groupId);

	/**
	* Returns all the registration forms where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching registration forms
	*/
	public java.util.List<RegistrationForm> findByUuid_C(String uuid,
		long companyId);

	/**
	* Returns a range of all the registration forms where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationFormModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of registration forms
	* @param end the upper bound of the range of registration forms (not inclusive)
	* @return the range of matching registration forms
	*/
	public java.util.List<RegistrationForm> findByUuid_C(String uuid,
		long companyId, int start, int end);

	/**
	* Returns an ordered range of all the registration forms where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationFormModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of registration forms
	* @param end the upper bound of the range of registration forms (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching registration forms
	*/
	public java.util.List<RegistrationForm> findByUuid_C(String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<RegistrationForm> orderByComparator);

	/**
	* Returns an ordered range of all the registration forms where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationFormModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of registration forms
	* @param end the upper bound of the range of registration forms (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching registration forms
	*/
	public java.util.List<RegistrationForm> findByUuid_C(String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<RegistrationForm> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first registration form in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching registration form
	* @throws NoSuchRegistrationFormException if a matching registration form could not be found
	*/
	public RegistrationForm findByUuid_C_First(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<RegistrationForm> orderByComparator)
		throws NoSuchRegistrationFormException;

	/**
	* Returns the first registration form in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching registration form, or <code>null</code> if a matching registration form could not be found
	*/
	public RegistrationForm fetchByUuid_C_First(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<RegistrationForm> orderByComparator);

	/**
	* Returns the last registration form in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching registration form
	* @throws NoSuchRegistrationFormException if a matching registration form could not be found
	*/
	public RegistrationForm findByUuid_C_Last(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<RegistrationForm> orderByComparator)
		throws NoSuchRegistrationFormException;

	/**
	* Returns the last registration form in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching registration form, or <code>null</code> if a matching registration form could not be found
	*/
	public RegistrationForm fetchByUuid_C_Last(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<RegistrationForm> orderByComparator);

	/**
	* Returns the registration forms before and after the current registration form in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param registrationFormId the primary key of the current registration form
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next registration form
	* @throws NoSuchRegistrationFormException if a registration form with the primary key could not be found
	*/
	public RegistrationForm[] findByUuid_C_PrevAndNext(
		long registrationFormId, String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<RegistrationForm> orderByComparator)
		throws NoSuchRegistrationFormException;

	/**
	* Removes all the registration forms where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(String uuid, long companyId);

	/**
	* Returns the number of registration forms where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching registration forms
	*/
	public int countByUuid_C(String uuid, long companyId);

	/**
	* Returns all the registration forms where groupId = &#63; and registrationId = &#63;.
	*
	* @param groupId the group ID
	* @param registrationId the registration ID
	* @return the matching registration forms
	*/
	public java.util.List<RegistrationForm> findByG_REGID(long groupId,
		long registrationId);

	/**
	* Returns a range of all the registration forms where groupId = &#63; and registrationId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationFormModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param registrationId the registration ID
	* @param start the lower bound of the range of registration forms
	* @param end the upper bound of the range of registration forms (not inclusive)
	* @return the range of matching registration forms
	*/
	public java.util.List<RegistrationForm> findByG_REGID(long groupId,
		long registrationId, int start, int end);

	/**
	* Returns an ordered range of all the registration forms where groupId = &#63; and registrationId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationFormModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param registrationId the registration ID
	* @param start the lower bound of the range of registration forms
	* @param end the upper bound of the range of registration forms (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching registration forms
	*/
	public java.util.List<RegistrationForm> findByG_REGID(long groupId,
		long registrationId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<RegistrationForm> orderByComparator);

	/**
	* Returns an ordered range of all the registration forms where groupId = &#63; and registrationId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationFormModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param registrationId the registration ID
	* @param start the lower bound of the range of registration forms
	* @param end the upper bound of the range of registration forms (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching registration forms
	*/
	public java.util.List<RegistrationForm> findByG_REGID(long groupId,
		long registrationId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<RegistrationForm> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first registration form in the ordered set where groupId = &#63; and registrationId = &#63;.
	*
	* @param groupId the group ID
	* @param registrationId the registration ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching registration form
	* @throws NoSuchRegistrationFormException if a matching registration form could not be found
	*/
	public RegistrationForm findByG_REGID_First(long groupId,
		long registrationId,
		com.liferay.portal.kernel.util.OrderByComparator<RegistrationForm> orderByComparator)
		throws NoSuchRegistrationFormException;

	/**
	* Returns the first registration form in the ordered set where groupId = &#63; and registrationId = &#63;.
	*
	* @param groupId the group ID
	* @param registrationId the registration ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching registration form, or <code>null</code> if a matching registration form could not be found
	*/
	public RegistrationForm fetchByG_REGID_First(long groupId,
		long registrationId,
		com.liferay.portal.kernel.util.OrderByComparator<RegistrationForm> orderByComparator);

	/**
	* Returns the last registration form in the ordered set where groupId = &#63; and registrationId = &#63;.
	*
	* @param groupId the group ID
	* @param registrationId the registration ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching registration form
	* @throws NoSuchRegistrationFormException if a matching registration form could not be found
	*/
	public RegistrationForm findByG_REGID_Last(long groupId,
		long registrationId,
		com.liferay.portal.kernel.util.OrderByComparator<RegistrationForm> orderByComparator)
		throws NoSuchRegistrationFormException;

	/**
	* Returns the last registration form in the ordered set where groupId = &#63; and registrationId = &#63;.
	*
	* @param groupId the group ID
	* @param registrationId the registration ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching registration form, or <code>null</code> if a matching registration form could not be found
	*/
	public RegistrationForm fetchByG_REGID_Last(long groupId,
		long registrationId,
		com.liferay.portal.kernel.util.OrderByComparator<RegistrationForm> orderByComparator);

	/**
	* Returns the registration forms before and after the current registration form in the ordered set where groupId = &#63; and registrationId = &#63;.
	*
	* @param registrationFormId the primary key of the current registration form
	* @param groupId the group ID
	* @param registrationId the registration ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next registration form
	* @throws NoSuchRegistrationFormException if a registration form with the primary key could not be found
	*/
	public RegistrationForm[] findByG_REGID_PrevAndNext(
		long registrationFormId, long groupId, long registrationId,
		com.liferay.portal.kernel.util.OrderByComparator<RegistrationForm> orderByComparator)
		throws NoSuchRegistrationFormException;

	/**
	* Removes all the registration forms where groupId = &#63; and registrationId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param registrationId the registration ID
	*/
	public void removeByG_REGID(long groupId, long registrationId);

	/**
	* Returns the number of registration forms where groupId = &#63; and registrationId = &#63;.
	*
	* @param groupId the group ID
	* @param registrationId the registration ID
	* @return the number of matching registration forms
	*/
	public int countByG_REGID(long groupId, long registrationId);

	/**
	* Returns the registration form where groupId = &#63; and registrationId = &#63; and referenceUid = &#63; or throws a {@link NoSuchRegistrationFormException} if it could not be found.
	*
	* @param groupId the group ID
	* @param registrationId the registration ID
	* @param referenceUid the reference uid
	* @return the matching registration form
	* @throws NoSuchRegistrationFormException if a matching registration form could not be found
	*/
	public RegistrationForm findByG_REGID_REFID(long groupId,
		long registrationId, String referenceUid)
		throws NoSuchRegistrationFormException;

	/**
	* Returns the registration form where groupId = &#63; and registrationId = &#63; and referenceUid = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param registrationId the registration ID
	* @param referenceUid the reference uid
	* @return the matching registration form, or <code>null</code> if a matching registration form could not be found
	*/
	public RegistrationForm fetchByG_REGID_REFID(long groupId,
		long registrationId, String referenceUid);

	/**
	* Returns the registration form where groupId = &#63; and registrationId = &#63; and referenceUid = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param registrationId the registration ID
	* @param referenceUid the reference uid
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching registration form, or <code>null</code> if a matching registration form could not be found
	*/
	public RegistrationForm fetchByG_REGID_REFID(long groupId,
		long registrationId, String referenceUid, boolean retrieveFromCache);

	/**
	* Removes the registration form where groupId = &#63; and registrationId = &#63; and referenceUid = &#63; from the database.
	*
	* @param groupId the group ID
	* @param registrationId the registration ID
	* @param referenceUid the reference uid
	* @return the registration form that was removed
	*/
	public RegistrationForm removeByG_REGID_REFID(long groupId,
		long registrationId, String referenceUid)
		throws NoSuchRegistrationFormException;

	/**
	* Returns the number of registration forms where groupId = &#63; and registrationId = &#63; and referenceUid = &#63;.
	*
	* @param groupId the group ID
	* @param registrationId the registration ID
	* @param referenceUid the reference uid
	* @return the number of matching registration forms
	*/
	public int countByG_REGID_REFID(long groupId, long registrationId,
		String referenceUid);

	/**
	* Returns all the registration forms where registrationId = &#63; and isNew = &#63;.
	*
	* @param registrationId the registration ID
	* @param isNew the is new
	* @return the matching registration forms
	*/
	public java.util.List<RegistrationForm> findByG_REGID_ISNEW(
		long registrationId, boolean isNew);

	/**
	* Returns a range of all the registration forms where registrationId = &#63; and isNew = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationFormModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param registrationId the registration ID
	* @param isNew the is new
	* @param start the lower bound of the range of registration forms
	* @param end the upper bound of the range of registration forms (not inclusive)
	* @return the range of matching registration forms
	*/
	public java.util.List<RegistrationForm> findByG_REGID_ISNEW(
		long registrationId, boolean isNew, int start, int end);

	/**
	* Returns an ordered range of all the registration forms where registrationId = &#63; and isNew = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationFormModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param registrationId the registration ID
	* @param isNew the is new
	* @param start the lower bound of the range of registration forms
	* @param end the upper bound of the range of registration forms (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching registration forms
	*/
	public java.util.List<RegistrationForm> findByG_REGID_ISNEW(
		long registrationId, boolean isNew, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<RegistrationForm> orderByComparator);

	/**
	* Returns an ordered range of all the registration forms where registrationId = &#63; and isNew = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationFormModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param registrationId the registration ID
	* @param isNew the is new
	* @param start the lower bound of the range of registration forms
	* @param end the upper bound of the range of registration forms (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching registration forms
	*/
	public java.util.List<RegistrationForm> findByG_REGID_ISNEW(
		long registrationId, boolean isNew, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<RegistrationForm> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first registration form in the ordered set where registrationId = &#63; and isNew = &#63;.
	*
	* @param registrationId the registration ID
	* @param isNew the is new
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching registration form
	* @throws NoSuchRegistrationFormException if a matching registration form could not be found
	*/
	public RegistrationForm findByG_REGID_ISNEW_First(long registrationId,
		boolean isNew,
		com.liferay.portal.kernel.util.OrderByComparator<RegistrationForm> orderByComparator)
		throws NoSuchRegistrationFormException;

	/**
	* Returns the first registration form in the ordered set where registrationId = &#63; and isNew = &#63;.
	*
	* @param registrationId the registration ID
	* @param isNew the is new
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching registration form, or <code>null</code> if a matching registration form could not be found
	*/
	public RegistrationForm fetchByG_REGID_ISNEW_First(long registrationId,
		boolean isNew,
		com.liferay.portal.kernel.util.OrderByComparator<RegistrationForm> orderByComparator);

	/**
	* Returns the last registration form in the ordered set where registrationId = &#63; and isNew = &#63;.
	*
	* @param registrationId the registration ID
	* @param isNew the is new
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching registration form
	* @throws NoSuchRegistrationFormException if a matching registration form could not be found
	*/
	public RegistrationForm findByG_REGID_ISNEW_Last(long registrationId,
		boolean isNew,
		com.liferay.portal.kernel.util.OrderByComparator<RegistrationForm> orderByComparator)
		throws NoSuchRegistrationFormException;

	/**
	* Returns the last registration form in the ordered set where registrationId = &#63; and isNew = &#63;.
	*
	* @param registrationId the registration ID
	* @param isNew the is new
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching registration form, or <code>null</code> if a matching registration form could not be found
	*/
	public RegistrationForm fetchByG_REGID_ISNEW_Last(long registrationId,
		boolean isNew,
		com.liferay.portal.kernel.util.OrderByComparator<RegistrationForm> orderByComparator);

	/**
	* Returns the registration forms before and after the current registration form in the ordered set where registrationId = &#63; and isNew = &#63;.
	*
	* @param registrationFormId the primary key of the current registration form
	* @param registrationId the registration ID
	* @param isNew the is new
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next registration form
	* @throws NoSuchRegistrationFormException if a registration form with the primary key could not be found
	*/
	public RegistrationForm[] findByG_REGID_ISNEW_PrevAndNext(
		long registrationFormId, long registrationId, boolean isNew,
		com.liferay.portal.kernel.util.OrderByComparator<RegistrationForm> orderByComparator)
		throws NoSuchRegistrationFormException;

	/**
	* Removes all the registration forms where registrationId = &#63; and isNew = &#63; from the database.
	*
	* @param registrationId the registration ID
	* @param isNew the is new
	*/
	public void removeByG_REGID_ISNEW(long registrationId, boolean isNew);

	/**
	* Returns the number of registration forms where registrationId = &#63; and isNew = &#63;.
	*
	* @param registrationId the registration ID
	* @param isNew the is new
	* @return the number of matching registration forms
	*/
	public int countByG_REGID_ISNEW(long registrationId, boolean isNew);

	/**
	* Returns the registration form where registrationId = &#63; and formNo = &#63; or throws a {@link NoSuchRegistrationFormException} if it could not be found.
	*
	* @param registrationId the registration ID
	* @param formNo the form no
	* @return the matching registration form
	* @throws NoSuchRegistrationFormException if a matching registration form could not be found
	*/
	public RegistrationForm findByREGID_FORMNO(long registrationId,
		String formNo) throws NoSuchRegistrationFormException;

	/**
	* Returns the registration form where registrationId = &#63; and formNo = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param registrationId the registration ID
	* @param formNo the form no
	* @return the matching registration form, or <code>null</code> if a matching registration form could not be found
	*/
	public RegistrationForm fetchByREGID_FORMNO(long registrationId,
		String formNo);

	/**
	* Returns the registration form where registrationId = &#63; and formNo = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param registrationId the registration ID
	* @param formNo the form no
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching registration form, or <code>null</code> if a matching registration form could not be found
	*/
	public RegistrationForm fetchByREGID_FORMNO(long registrationId,
		String formNo, boolean retrieveFromCache);

	/**
	* Removes the registration form where registrationId = &#63; and formNo = &#63; from the database.
	*
	* @param registrationId the registration ID
	* @param formNo the form no
	* @return the registration form that was removed
	*/
	public RegistrationForm removeByREGID_FORMNO(long registrationId,
		String formNo) throws NoSuchRegistrationFormException;

	/**
	* Returns the number of registration forms where registrationId = &#63; and formNo = &#63;.
	*
	* @param registrationId the registration ID
	* @param formNo the form no
	* @return the number of matching registration forms
	*/
	public int countByREGID_FORMNO(long registrationId, String formNo);

	/**
	* Returns all the registration forms where groupId = &#63; and registrationId = &#63; and formNo = &#63;.
	*
	* @param groupId the group ID
	* @param registrationId the registration ID
	* @param formNo the form no
	* @return the matching registration forms
	*/
	public java.util.List<RegistrationForm> findByG_REGID_FORMNO(long groupId,
		long registrationId, String formNo);

	/**
	* Returns a range of all the registration forms where groupId = &#63; and registrationId = &#63; and formNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationFormModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param registrationId the registration ID
	* @param formNo the form no
	* @param start the lower bound of the range of registration forms
	* @param end the upper bound of the range of registration forms (not inclusive)
	* @return the range of matching registration forms
	*/
	public java.util.List<RegistrationForm> findByG_REGID_FORMNO(long groupId,
		long registrationId, String formNo, int start, int end);

	/**
	* Returns an ordered range of all the registration forms where groupId = &#63; and registrationId = &#63; and formNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationFormModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param registrationId the registration ID
	* @param formNo the form no
	* @param start the lower bound of the range of registration forms
	* @param end the upper bound of the range of registration forms (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching registration forms
	*/
	public java.util.List<RegistrationForm> findByG_REGID_FORMNO(long groupId,
		long registrationId, String formNo, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<RegistrationForm> orderByComparator);

	/**
	* Returns an ordered range of all the registration forms where groupId = &#63; and registrationId = &#63; and formNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationFormModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param registrationId the registration ID
	* @param formNo the form no
	* @param start the lower bound of the range of registration forms
	* @param end the upper bound of the range of registration forms (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching registration forms
	*/
	public java.util.List<RegistrationForm> findByG_REGID_FORMNO(long groupId,
		long registrationId, String formNo, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<RegistrationForm> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first registration form in the ordered set where groupId = &#63; and registrationId = &#63; and formNo = &#63;.
	*
	* @param groupId the group ID
	* @param registrationId the registration ID
	* @param formNo the form no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching registration form
	* @throws NoSuchRegistrationFormException if a matching registration form could not be found
	*/
	public RegistrationForm findByG_REGID_FORMNO_First(long groupId,
		long registrationId, String formNo,
		com.liferay.portal.kernel.util.OrderByComparator<RegistrationForm> orderByComparator)
		throws NoSuchRegistrationFormException;

	/**
	* Returns the first registration form in the ordered set where groupId = &#63; and registrationId = &#63; and formNo = &#63;.
	*
	* @param groupId the group ID
	* @param registrationId the registration ID
	* @param formNo the form no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching registration form, or <code>null</code> if a matching registration form could not be found
	*/
	public RegistrationForm fetchByG_REGID_FORMNO_First(long groupId,
		long registrationId, String formNo,
		com.liferay.portal.kernel.util.OrderByComparator<RegistrationForm> orderByComparator);

	/**
	* Returns the last registration form in the ordered set where groupId = &#63; and registrationId = &#63; and formNo = &#63;.
	*
	* @param groupId the group ID
	* @param registrationId the registration ID
	* @param formNo the form no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching registration form
	* @throws NoSuchRegistrationFormException if a matching registration form could not be found
	*/
	public RegistrationForm findByG_REGID_FORMNO_Last(long groupId,
		long registrationId, String formNo,
		com.liferay.portal.kernel.util.OrderByComparator<RegistrationForm> orderByComparator)
		throws NoSuchRegistrationFormException;

	/**
	* Returns the last registration form in the ordered set where groupId = &#63; and registrationId = &#63; and formNo = &#63;.
	*
	* @param groupId the group ID
	* @param registrationId the registration ID
	* @param formNo the form no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching registration form, or <code>null</code> if a matching registration form could not be found
	*/
	public RegistrationForm fetchByG_REGID_FORMNO_Last(long groupId,
		long registrationId, String formNo,
		com.liferay.portal.kernel.util.OrderByComparator<RegistrationForm> orderByComparator);

	/**
	* Returns the registration forms before and after the current registration form in the ordered set where groupId = &#63; and registrationId = &#63; and formNo = &#63;.
	*
	* @param registrationFormId the primary key of the current registration form
	* @param groupId the group ID
	* @param registrationId the registration ID
	* @param formNo the form no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next registration form
	* @throws NoSuchRegistrationFormException if a registration form with the primary key could not be found
	*/
	public RegistrationForm[] findByG_REGID_FORMNO_PrevAndNext(
		long registrationFormId, long groupId, long registrationId,
		String formNo,
		com.liferay.portal.kernel.util.OrderByComparator<RegistrationForm> orderByComparator)
		throws NoSuchRegistrationFormException;

	/**
	* Removes all the registration forms where groupId = &#63; and registrationId = &#63; and formNo = &#63; from the database.
	*
	* @param groupId the group ID
	* @param registrationId the registration ID
	* @param formNo the form no
	*/
	public void removeByG_REGID_FORMNO(long groupId, long registrationId,
		String formNo);

	/**
	* Returns the number of registration forms where groupId = &#63; and registrationId = &#63; and formNo = &#63;.
	*
	* @param groupId the group ID
	* @param registrationId the registration ID
	* @param formNo the form no
	* @return the number of matching registration forms
	*/
	public int countByG_REGID_FORMNO(long groupId, long registrationId,
		String formNo);

	/**
	* Returns all the registration forms where referenceUid = &#63;.
	*
	* @param referenceUid the reference uid
	* @return the matching registration forms
	*/
	public java.util.List<RegistrationForm> findByF_REFID(String referenceUid);

	/**
	* Returns a range of all the registration forms where referenceUid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationFormModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param referenceUid the reference uid
	* @param start the lower bound of the range of registration forms
	* @param end the upper bound of the range of registration forms (not inclusive)
	* @return the range of matching registration forms
	*/
	public java.util.List<RegistrationForm> findByF_REFID(String referenceUid,
		int start, int end);

	/**
	* Returns an ordered range of all the registration forms where referenceUid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationFormModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param referenceUid the reference uid
	* @param start the lower bound of the range of registration forms
	* @param end the upper bound of the range of registration forms (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching registration forms
	*/
	public java.util.List<RegistrationForm> findByF_REFID(String referenceUid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<RegistrationForm> orderByComparator);

	/**
	* Returns an ordered range of all the registration forms where referenceUid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationFormModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param referenceUid the reference uid
	* @param start the lower bound of the range of registration forms
	* @param end the upper bound of the range of registration forms (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching registration forms
	*/
	public java.util.List<RegistrationForm> findByF_REFID(String referenceUid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<RegistrationForm> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first registration form in the ordered set where referenceUid = &#63;.
	*
	* @param referenceUid the reference uid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching registration form
	* @throws NoSuchRegistrationFormException if a matching registration form could not be found
	*/
	public RegistrationForm findByF_REFID_First(String referenceUid,
		com.liferay.portal.kernel.util.OrderByComparator<RegistrationForm> orderByComparator)
		throws NoSuchRegistrationFormException;

	/**
	* Returns the first registration form in the ordered set where referenceUid = &#63;.
	*
	* @param referenceUid the reference uid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching registration form, or <code>null</code> if a matching registration form could not be found
	*/
	public RegistrationForm fetchByF_REFID_First(String referenceUid,
		com.liferay.portal.kernel.util.OrderByComparator<RegistrationForm> orderByComparator);

	/**
	* Returns the last registration form in the ordered set where referenceUid = &#63;.
	*
	* @param referenceUid the reference uid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching registration form
	* @throws NoSuchRegistrationFormException if a matching registration form could not be found
	*/
	public RegistrationForm findByF_REFID_Last(String referenceUid,
		com.liferay.portal.kernel.util.OrderByComparator<RegistrationForm> orderByComparator)
		throws NoSuchRegistrationFormException;

	/**
	* Returns the last registration form in the ordered set where referenceUid = &#63;.
	*
	* @param referenceUid the reference uid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching registration form, or <code>null</code> if a matching registration form could not be found
	*/
	public RegistrationForm fetchByF_REFID_Last(String referenceUid,
		com.liferay.portal.kernel.util.OrderByComparator<RegistrationForm> orderByComparator);

	/**
	* Returns the registration forms before and after the current registration form in the ordered set where referenceUid = &#63;.
	*
	* @param registrationFormId the primary key of the current registration form
	* @param referenceUid the reference uid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next registration form
	* @throws NoSuchRegistrationFormException if a registration form with the primary key could not be found
	*/
	public RegistrationForm[] findByF_REFID_PrevAndNext(
		long registrationFormId, String referenceUid,
		com.liferay.portal.kernel.util.OrderByComparator<RegistrationForm> orderByComparator)
		throws NoSuchRegistrationFormException;

	/**
	* Removes all the registration forms where referenceUid = &#63; from the database.
	*
	* @param referenceUid the reference uid
	*/
	public void removeByF_REFID(String referenceUid);

	/**
	* Returns the number of registration forms where referenceUid = &#63;.
	*
	* @param referenceUid the reference uid
	* @return the number of matching registration forms
	*/
	public int countByF_REFID(String referenceUid);

	/**
	* Caches the registration form in the entity cache if it is enabled.
	*
	* @param registrationForm the registration form
	*/
	public void cacheResult(RegistrationForm registrationForm);

	/**
	* Caches the registration forms in the entity cache if it is enabled.
	*
	* @param registrationForms the registration forms
	*/
	public void cacheResult(java.util.List<RegistrationForm> registrationForms);

	/**
	* Creates a new registration form with the primary key. Does not add the registration form to the database.
	*
	* @param registrationFormId the primary key for the new registration form
	* @return the new registration form
	*/
	public RegistrationForm create(long registrationFormId);

	/**
	* Removes the registration form with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param registrationFormId the primary key of the registration form
	* @return the registration form that was removed
	* @throws NoSuchRegistrationFormException if a registration form with the primary key could not be found
	*/
	public RegistrationForm remove(long registrationFormId)
		throws NoSuchRegistrationFormException;

	public RegistrationForm updateImpl(RegistrationForm registrationForm);

	/**
	* Returns the registration form with the primary key or throws a {@link NoSuchRegistrationFormException} if it could not be found.
	*
	* @param registrationFormId the primary key of the registration form
	* @return the registration form
	* @throws NoSuchRegistrationFormException if a registration form with the primary key could not be found
	*/
	public RegistrationForm findByPrimaryKey(long registrationFormId)
		throws NoSuchRegistrationFormException;

	/**
	* Returns the registration form with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param registrationFormId the primary key of the registration form
	* @return the registration form, or <code>null</code> if a registration form with the primary key could not be found
	*/
	public RegistrationForm fetchByPrimaryKey(long registrationFormId);

	@Override
	public java.util.Map<java.io.Serializable, RegistrationForm> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the registration forms.
	*
	* @return the registration forms
	*/
	public java.util.List<RegistrationForm> findAll();

	/**
	* Returns a range of all the registration forms.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationFormModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of registration forms
	* @param end the upper bound of the range of registration forms (not inclusive)
	* @return the range of registration forms
	*/
	public java.util.List<RegistrationForm> findAll(int start, int end);

	/**
	* Returns an ordered range of all the registration forms.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationFormModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of registration forms
	* @param end the upper bound of the range of registration forms (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of registration forms
	*/
	public java.util.List<RegistrationForm> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<RegistrationForm> orderByComparator);

	/**
	* Returns an ordered range of all the registration forms.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationFormModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of registration forms
	* @param end the upper bound of the range of registration forms (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of registration forms
	*/
	public java.util.List<RegistrationForm> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<RegistrationForm> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the registration forms from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of registration forms.
	*
	* @return the number of registration forms
	*/
	public int countAll();

	@Override
	public java.util.Set<String> getBadColumnNames();
}