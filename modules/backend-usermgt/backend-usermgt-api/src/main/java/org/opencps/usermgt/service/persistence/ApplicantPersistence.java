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

import org.opencps.usermgt.exception.NoSuchApplicantException;
import org.opencps.usermgt.model.Applicant;

/**
 * The persistence interface for the applicant service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author khoavu
 * @see org.opencps.usermgt.service.persistence.impl.ApplicantPersistenceImpl
 * @see ApplicantUtil
 * @generated
 */
@ProviderType
public interface ApplicantPersistence extends BasePersistence<Applicant> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ApplicantUtil} to access the applicant persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the applicants where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching applicants
	*/
	public java.util.List<Applicant> findByUuid(String uuid);

	/**
	* Returns a range of all the applicants where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ApplicantModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of applicants
	* @param end the upper bound of the range of applicants (not inclusive)
	* @return the range of matching applicants
	*/
	public java.util.List<Applicant> findByUuid(String uuid, int start, int end);

	/**
	* Returns an ordered range of all the applicants where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ApplicantModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of applicants
	* @param end the upper bound of the range of applicants (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching applicants
	*/
	public java.util.List<Applicant> findByUuid(String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Applicant> orderByComparator);

	/**
	* Returns an ordered range of all the applicants where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ApplicantModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of applicants
	* @param end the upper bound of the range of applicants (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching applicants
	*/
	public java.util.List<Applicant> findByUuid(String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Applicant> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first applicant in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching applicant
	* @throws NoSuchApplicantException if a matching applicant could not be found
	*/
	public Applicant findByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Applicant> orderByComparator)
		throws NoSuchApplicantException;

	/**
	* Returns the first applicant in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching applicant, or <code>null</code> if a matching applicant could not be found
	*/
	public Applicant fetchByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Applicant> orderByComparator);

	/**
	* Returns the last applicant in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching applicant
	* @throws NoSuchApplicantException if a matching applicant could not be found
	*/
	public Applicant findByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Applicant> orderByComparator)
		throws NoSuchApplicantException;

	/**
	* Returns the last applicant in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching applicant, or <code>null</code> if a matching applicant could not be found
	*/
	public Applicant fetchByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Applicant> orderByComparator);

	/**
	* Returns the applicants before and after the current applicant in the ordered set where uuid = &#63;.
	*
	* @param applicantId the primary key of the current applicant
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next applicant
	* @throws NoSuchApplicantException if a applicant with the primary key could not be found
	*/
	public Applicant[] findByUuid_PrevAndNext(long applicantId, String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Applicant> orderByComparator)
		throws NoSuchApplicantException;

	/**
	* Removes all the applicants where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(String uuid);

	/**
	* Returns the number of applicants where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching applicants
	*/
	public int countByUuid(String uuid);

	/**
	* Returns the applicant where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchApplicantException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching applicant
	* @throws NoSuchApplicantException if a matching applicant could not be found
	*/
	public Applicant findByUUID_G(String uuid, long groupId)
		throws NoSuchApplicantException;

	/**
	* Returns the applicant where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching applicant, or <code>null</code> if a matching applicant could not be found
	*/
	public Applicant fetchByUUID_G(String uuid, long groupId);

	/**
	* Returns the applicant where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching applicant, or <code>null</code> if a matching applicant could not be found
	*/
	public Applicant fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the applicant where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the applicant that was removed
	*/
	public Applicant removeByUUID_G(String uuid, long groupId)
		throws NoSuchApplicantException;

	/**
	* Returns the number of applicants where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching applicants
	*/
	public int countByUUID_G(String uuid, long groupId);

	/**
	* Returns all the applicants where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching applicants
	*/
	public java.util.List<Applicant> findByUuid_C(String uuid, long companyId);

	/**
	* Returns a range of all the applicants where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ApplicantModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of applicants
	* @param end the upper bound of the range of applicants (not inclusive)
	* @return the range of matching applicants
	*/
	public java.util.List<Applicant> findByUuid_C(String uuid, long companyId,
		int start, int end);

	/**
	* Returns an ordered range of all the applicants where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ApplicantModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of applicants
	* @param end the upper bound of the range of applicants (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching applicants
	*/
	public java.util.List<Applicant> findByUuid_C(String uuid, long companyId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Applicant> orderByComparator);

	/**
	* Returns an ordered range of all the applicants where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ApplicantModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of applicants
	* @param end the upper bound of the range of applicants (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching applicants
	*/
	public java.util.List<Applicant> findByUuid_C(String uuid, long companyId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Applicant> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first applicant in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching applicant
	* @throws NoSuchApplicantException if a matching applicant could not be found
	*/
	public Applicant findByUuid_C_First(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Applicant> orderByComparator)
		throws NoSuchApplicantException;

	/**
	* Returns the first applicant in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching applicant, or <code>null</code> if a matching applicant could not be found
	*/
	public Applicant fetchByUuid_C_First(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Applicant> orderByComparator);

	/**
	* Returns the last applicant in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching applicant
	* @throws NoSuchApplicantException if a matching applicant could not be found
	*/
	public Applicant findByUuid_C_Last(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Applicant> orderByComparator)
		throws NoSuchApplicantException;

	/**
	* Returns the last applicant in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching applicant, or <code>null</code> if a matching applicant could not be found
	*/
	public Applicant fetchByUuid_C_Last(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Applicant> orderByComparator);

	/**
	* Returns the applicants before and after the current applicant in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param applicantId the primary key of the current applicant
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next applicant
	* @throws NoSuchApplicantException if a applicant with the primary key could not be found
	*/
	public Applicant[] findByUuid_C_PrevAndNext(long applicantId, String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Applicant> orderByComparator)
		throws NoSuchApplicantException;

	/**
	* Removes all the applicants where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(String uuid, long companyId);

	/**
	* Returns the number of applicants where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching applicants
	*/
	public int countByUuid_C(String uuid, long companyId);

	/**
	* Returns the applicant where applicantIdNo = &#63; or throws a {@link NoSuchApplicantException} if it could not be found.
	*
	* @param applicantIdNo the applicant ID no
	* @return the matching applicant
	* @throws NoSuchApplicantException if a matching applicant could not be found
	*/
	public Applicant findByF_APLC_ID(String applicantIdNo)
		throws NoSuchApplicantException;

	/**
	* Returns the applicant where applicantIdNo = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param applicantIdNo the applicant ID no
	* @return the matching applicant, or <code>null</code> if a matching applicant could not be found
	*/
	public Applicant fetchByF_APLC_ID(String applicantIdNo);

	/**
	* Returns the applicant where applicantIdNo = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param applicantIdNo the applicant ID no
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching applicant, or <code>null</code> if a matching applicant could not be found
	*/
	public Applicant fetchByF_APLC_ID(String applicantIdNo,
		boolean retrieveFromCache);

	/**
	* Removes the applicant where applicantIdNo = &#63; from the database.
	*
	* @param applicantIdNo the applicant ID no
	* @return the applicant that was removed
	*/
	public Applicant removeByF_APLC_ID(String applicantIdNo)
		throws NoSuchApplicantException;

	/**
	* Returns the number of applicants where applicantIdNo = &#63;.
	*
	* @param applicantIdNo the applicant ID no
	* @return the number of matching applicants
	*/
	public int countByF_APLC_ID(String applicantIdNo);

	/**
	* Returns the applicant where groupId = &#63; and applicantIdNo = &#63; or throws a {@link NoSuchApplicantException} if it could not be found.
	*
	* @param groupId the group ID
	* @param applicantIdNo the applicant ID no
	* @return the matching applicant
	* @throws NoSuchApplicantException if a matching applicant could not be found
	*/
	public Applicant findByF_APLC_GID(long groupId, String applicantIdNo)
		throws NoSuchApplicantException;

	/**
	* Returns the applicant where groupId = &#63; and applicantIdNo = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param applicantIdNo the applicant ID no
	* @return the matching applicant, or <code>null</code> if a matching applicant could not be found
	*/
	public Applicant fetchByF_APLC_GID(long groupId, String applicantIdNo);

	/**
	* Returns the applicant where groupId = &#63; and applicantIdNo = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param applicantIdNo the applicant ID no
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching applicant, or <code>null</code> if a matching applicant could not be found
	*/
	public Applicant fetchByF_APLC_GID(long groupId, String applicantIdNo,
		boolean retrieveFromCache);

	/**
	* Removes the applicant where groupId = &#63; and applicantIdNo = &#63; from the database.
	*
	* @param groupId the group ID
	* @param applicantIdNo the applicant ID no
	* @return the applicant that was removed
	*/
	public Applicant removeByF_APLC_GID(long groupId, String applicantIdNo)
		throws NoSuchApplicantException;

	/**
	* Returns the number of applicants where groupId = &#63; and applicantIdNo = &#63;.
	*
	* @param groupId the group ID
	* @param applicantIdNo the applicant ID no
	* @return the number of matching applicants
	*/
	public int countByF_APLC_GID(long groupId, String applicantIdNo);

	/**
	* Returns the applicant where contactTelNo = &#63; or throws a {@link NoSuchApplicantException} if it could not be found.
	*
	* @param contactTelNo the contact tel no
	* @return the matching applicant
	* @throws NoSuchApplicantException if a matching applicant could not be found
	*/
	public Applicant findByF_CTT_ID(String contactTelNo)
		throws NoSuchApplicantException;

	/**
	* Returns the applicant where contactTelNo = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param contactTelNo the contact tel no
	* @return the matching applicant, or <code>null</code> if a matching applicant could not be found
	*/
	public Applicant fetchByF_CTT_ID(String contactTelNo);

	/**
	* Returns the applicant where contactTelNo = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param contactTelNo the contact tel no
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching applicant, or <code>null</code> if a matching applicant could not be found
	*/
	public Applicant fetchByF_CTT_ID(String contactTelNo,
		boolean retrieveFromCache);

	/**
	* Removes the applicant where contactTelNo = &#63; from the database.
	*
	* @param contactTelNo the contact tel no
	* @return the applicant that was removed
	*/
	public Applicant removeByF_CTT_ID(String contactTelNo)
		throws NoSuchApplicantException;

	/**
	* Returns the number of applicants where contactTelNo = &#63;.
	*
	* @param contactTelNo the contact tel no
	* @return the number of matching applicants
	*/
	public int countByF_CTT_ID(String contactTelNo);

	/**
	* Returns the applicant where contactEmail = &#63; or throws a {@link NoSuchApplicantException} if it could not be found.
	*
	* @param contactEmail the contact email
	* @return the matching applicant
	* @throws NoSuchApplicantException if a matching applicant could not be found
	*/
	public Applicant findByF_CTE_ID(String contactEmail)
		throws NoSuchApplicantException;

	/**
	* Returns the applicant where contactEmail = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param contactEmail the contact email
	* @return the matching applicant, or <code>null</code> if a matching applicant could not be found
	*/
	public Applicant fetchByF_CTE_ID(String contactEmail);

	/**
	* Returns the applicant where contactEmail = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param contactEmail the contact email
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching applicant, or <code>null</code> if a matching applicant could not be found
	*/
	public Applicant fetchByF_CTE_ID(String contactEmail,
		boolean retrieveFromCache);

	/**
	* Removes the applicant where contactEmail = &#63; from the database.
	*
	* @param contactEmail the contact email
	* @return the applicant that was removed
	*/
	public Applicant removeByF_CTE_ID(String contactEmail)
		throws NoSuchApplicantException;

	/**
	* Returns the number of applicants where contactEmail = &#63;.
	*
	* @param contactEmail the contact email
	* @return the number of matching applicants
	*/
	public int countByF_CTE_ID(String contactEmail);

	/**
	* Returns the applicant where mappingUserId = &#63; or throws a {@link NoSuchApplicantException} if it could not be found.
	*
	* @param mappingUserId the mapping user ID
	* @return the matching applicant
	* @throws NoSuchApplicantException if a matching applicant could not be found
	*/
	public Applicant findByF_MAPPING_ID(long mappingUserId)
		throws NoSuchApplicantException;

	/**
	* Returns the applicant where mappingUserId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param mappingUserId the mapping user ID
	* @return the matching applicant, or <code>null</code> if a matching applicant could not be found
	*/
	public Applicant fetchByF_MAPPING_ID(long mappingUserId);

	/**
	* Returns the applicant where mappingUserId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param mappingUserId the mapping user ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching applicant, or <code>null</code> if a matching applicant could not be found
	*/
	public Applicant fetchByF_MAPPING_ID(long mappingUserId,
		boolean retrieveFromCache);

	/**
	* Removes the applicant where mappingUserId = &#63; from the database.
	*
	* @param mappingUserId the mapping user ID
	* @return the applicant that was removed
	*/
	public Applicant removeByF_MAPPING_ID(long mappingUserId)
		throws NoSuchApplicantException;

	/**
	* Returns the number of applicants where mappingUserId = &#63;.
	*
	* @param mappingUserId the mapping user ID
	* @return the number of matching applicants
	*/
	public int countByF_MAPPING_ID(long mappingUserId);

	/**
	* Caches the applicant in the entity cache if it is enabled.
	*
	* @param applicant the applicant
	*/
	public void cacheResult(Applicant applicant);

	/**
	* Caches the applicants in the entity cache if it is enabled.
	*
	* @param applicants the applicants
	*/
	public void cacheResult(java.util.List<Applicant> applicants);

	/**
	* Creates a new applicant with the primary key. Does not add the applicant to the database.
	*
	* @param applicantId the primary key for the new applicant
	* @return the new applicant
	*/
	public Applicant create(long applicantId);

	/**
	* Removes the applicant with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param applicantId the primary key of the applicant
	* @return the applicant that was removed
	* @throws NoSuchApplicantException if a applicant with the primary key could not be found
	*/
	public Applicant remove(long applicantId) throws NoSuchApplicantException;

	public Applicant updateImpl(Applicant applicant);

	/**
	* Returns the applicant with the primary key or throws a {@link NoSuchApplicantException} if it could not be found.
	*
	* @param applicantId the primary key of the applicant
	* @return the applicant
	* @throws NoSuchApplicantException if a applicant with the primary key could not be found
	*/
	public Applicant findByPrimaryKey(long applicantId)
		throws NoSuchApplicantException;

	/**
	* Returns the applicant with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param applicantId the primary key of the applicant
	* @return the applicant, or <code>null</code> if a applicant with the primary key could not be found
	*/
	public Applicant fetchByPrimaryKey(long applicantId);

	@Override
	public java.util.Map<java.io.Serializable, Applicant> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the applicants.
	*
	* @return the applicants
	*/
	public java.util.List<Applicant> findAll();

	/**
	* Returns a range of all the applicants.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ApplicantModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of applicants
	* @param end the upper bound of the range of applicants (not inclusive)
	* @return the range of applicants
	*/
	public java.util.List<Applicant> findAll(int start, int end);

	/**
	* Returns an ordered range of all the applicants.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ApplicantModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of applicants
	* @param end the upper bound of the range of applicants (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of applicants
	*/
	public java.util.List<Applicant> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Applicant> orderByComparator);

	/**
	* Returns an ordered range of all the applicants.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ApplicantModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of applicants
	* @param end the upper bound of the range of applicants (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of applicants
	*/
	public java.util.List<Applicant> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Applicant> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the applicants from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of applicants.
	*
	* @return the number of applicants
	*/
	public int countAll();

	@Override
	public java.util.Set<String> getBadColumnNames();
}