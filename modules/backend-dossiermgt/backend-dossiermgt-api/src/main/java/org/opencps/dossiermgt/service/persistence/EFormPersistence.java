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

import org.opencps.dossiermgt.exception.NoSuchEFormException;
import org.opencps.dossiermgt.model.EForm;

/**
 * The persistence interface for the e form service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author huymq
 * @see org.opencps.dossiermgt.service.persistence.impl.EFormPersistenceImpl
 * @see EFormUtil
 * @generated
 */
@ProviderType
public interface EFormPersistence extends BasePersistence<EForm> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link EFormUtil} to access the e form persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the e forms where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching e forms
	*/
	public java.util.List<EForm> findByUuid(String uuid);

	/**
	* Returns a range of all the e forms where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EFormModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of e forms
	* @param end the upper bound of the range of e forms (not inclusive)
	* @return the range of matching e forms
	*/
	public java.util.List<EForm> findByUuid(String uuid, int start, int end);

	/**
	* Returns an ordered range of all the e forms where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EFormModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of e forms
	* @param end the upper bound of the range of e forms (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching e forms
	*/
	public java.util.List<EForm> findByUuid(String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<EForm> orderByComparator);

	/**
	* Returns an ordered range of all the e forms where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EFormModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of e forms
	* @param end the upper bound of the range of e forms (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching e forms
	*/
	public java.util.List<EForm> findByUuid(String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<EForm> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first e form in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching e form
	* @throws NoSuchEFormException if a matching e form could not be found
	*/
	public EForm findByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<EForm> orderByComparator)
		throws NoSuchEFormException;

	/**
	* Returns the first e form in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching e form, or <code>null</code> if a matching e form could not be found
	*/
	public EForm fetchByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<EForm> orderByComparator);

	/**
	* Returns the last e form in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching e form
	* @throws NoSuchEFormException if a matching e form could not be found
	*/
	public EForm findByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<EForm> orderByComparator)
		throws NoSuchEFormException;

	/**
	* Returns the last e form in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching e form, or <code>null</code> if a matching e form could not be found
	*/
	public EForm fetchByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<EForm> orderByComparator);

	/**
	* Returns the e forms before and after the current e form in the ordered set where uuid = &#63;.
	*
	* @param eFormId the primary key of the current e form
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next e form
	* @throws NoSuchEFormException if a e form with the primary key could not be found
	*/
	public EForm[] findByUuid_PrevAndNext(long eFormId, String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<EForm> orderByComparator)
		throws NoSuchEFormException;

	/**
	* Removes all the e forms where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(String uuid);

	/**
	* Returns the number of e forms where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching e forms
	*/
	public int countByUuid(String uuid);

	/**
	* Returns the e form where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchEFormException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching e form
	* @throws NoSuchEFormException if a matching e form could not be found
	*/
	public EForm findByUUID_G(String uuid, long groupId)
		throws NoSuchEFormException;

	/**
	* Returns the e form where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching e form, or <code>null</code> if a matching e form could not be found
	*/
	public EForm fetchByUUID_G(String uuid, long groupId);

	/**
	* Returns the e form where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching e form, or <code>null</code> if a matching e form could not be found
	*/
	public EForm fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the e form where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the e form that was removed
	*/
	public EForm removeByUUID_G(String uuid, long groupId)
		throws NoSuchEFormException;

	/**
	* Returns the number of e forms where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching e forms
	*/
	public int countByUUID_G(String uuid, long groupId);

	/**
	* Returns all the e forms where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching e forms
	*/
	public java.util.List<EForm> findByUuid_C(String uuid, long companyId);

	/**
	* Returns a range of all the e forms where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EFormModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of e forms
	* @param end the upper bound of the range of e forms (not inclusive)
	* @return the range of matching e forms
	*/
	public java.util.List<EForm> findByUuid_C(String uuid, long companyId,
		int start, int end);

	/**
	* Returns an ordered range of all the e forms where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EFormModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of e forms
	* @param end the upper bound of the range of e forms (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching e forms
	*/
	public java.util.List<EForm> findByUuid_C(String uuid, long companyId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<EForm> orderByComparator);

	/**
	* Returns an ordered range of all the e forms where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EFormModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of e forms
	* @param end the upper bound of the range of e forms (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching e forms
	*/
	public java.util.List<EForm> findByUuid_C(String uuid, long companyId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<EForm> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first e form in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching e form
	* @throws NoSuchEFormException if a matching e form could not be found
	*/
	public EForm findByUuid_C_First(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<EForm> orderByComparator)
		throws NoSuchEFormException;

	/**
	* Returns the first e form in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching e form, or <code>null</code> if a matching e form could not be found
	*/
	public EForm fetchByUuid_C_First(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<EForm> orderByComparator);

	/**
	* Returns the last e form in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching e form
	* @throws NoSuchEFormException if a matching e form could not be found
	*/
	public EForm findByUuid_C_Last(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<EForm> orderByComparator)
		throws NoSuchEFormException;

	/**
	* Returns the last e form in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching e form, or <code>null</code> if a matching e form could not be found
	*/
	public EForm fetchByUuid_C_Last(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<EForm> orderByComparator);

	/**
	* Returns the e forms before and after the current e form in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param eFormId the primary key of the current e form
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next e form
	* @throws NoSuchEFormException if a e form with the primary key could not be found
	*/
	public EForm[] findByUuid_C_PrevAndNext(long eFormId, String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<EForm> orderByComparator)
		throws NoSuchEFormException;

	/**
	* Removes all the e forms where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(String uuid, long companyId);

	/**
	* Returns the number of e forms where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching e forms
	*/
	public int countByUuid_C(String uuid, long companyId);

	/**
	* Returns all the e forms where groupId = &#63; and serviceCode = &#63;.
	*
	* @param groupId the group ID
	* @param serviceCode the service code
	* @return the matching e forms
	*/
	public java.util.List<EForm> findByF_GID_SC(long groupId, String serviceCode);

	/**
	* Returns a range of all the e forms where groupId = &#63; and serviceCode = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EFormModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param serviceCode the service code
	* @param start the lower bound of the range of e forms
	* @param end the upper bound of the range of e forms (not inclusive)
	* @return the range of matching e forms
	*/
	public java.util.List<EForm> findByF_GID_SC(long groupId,
		String serviceCode, int start, int end);

	/**
	* Returns an ordered range of all the e forms where groupId = &#63; and serviceCode = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EFormModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param serviceCode the service code
	* @param start the lower bound of the range of e forms
	* @param end the upper bound of the range of e forms (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching e forms
	*/
	public java.util.List<EForm> findByF_GID_SC(long groupId,
		String serviceCode, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<EForm> orderByComparator);

	/**
	* Returns an ordered range of all the e forms where groupId = &#63; and serviceCode = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EFormModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param serviceCode the service code
	* @param start the lower bound of the range of e forms
	* @param end the upper bound of the range of e forms (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching e forms
	*/
	public java.util.List<EForm> findByF_GID_SC(long groupId,
		String serviceCode, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<EForm> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first e form in the ordered set where groupId = &#63; and serviceCode = &#63;.
	*
	* @param groupId the group ID
	* @param serviceCode the service code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching e form
	* @throws NoSuchEFormException if a matching e form could not be found
	*/
	public EForm findByF_GID_SC_First(long groupId, String serviceCode,
		com.liferay.portal.kernel.util.OrderByComparator<EForm> orderByComparator)
		throws NoSuchEFormException;

	/**
	* Returns the first e form in the ordered set where groupId = &#63; and serviceCode = &#63;.
	*
	* @param groupId the group ID
	* @param serviceCode the service code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching e form, or <code>null</code> if a matching e form could not be found
	*/
	public EForm fetchByF_GID_SC_First(long groupId, String serviceCode,
		com.liferay.portal.kernel.util.OrderByComparator<EForm> orderByComparator);

	/**
	* Returns the last e form in the ordered set where groupId = &#63; and serviceCode = &#63;.
	*
	* @param groupId the group ID
	* @param serviceCode the service code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching e form
	* @throws NoSuchEFormException if a matching e form could not be found
	*/
	public EForm findByF_GID_SC_Last(long groupId, String serviceCode,
		com.liferay.portal.kernel.util.OrderByComparator<EForm> orderByComparator)
		throws NoSuchEFormException;

	/**
	* Returns the last e form in the ordered set where groupId = &#63; and serviceCode = &#63;.
	*
	* @param groupId the group ID
	* @param serviceCode the service code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching e form, or <code>null</code> if a matching e form could not be found
	*/
	public EForm fetchByF_GID_SC_Last(long groupId, String serviceCode,
		com.liferay.portal.kernel.util.OrderByComparator<EForm> orderByComparator);

	/**
	* Returns the e forms before and after the current e form in the ordered set where groupId = &#63; and serviceCode = &#63;.
	*
	* @param eFormId the primary key of the current e form
	* @param groupId the group ID
	* @param serviceCode the service code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next e form
	* @throws NoSuchEFormException if a e form with the primary key could not be found
	*/
	public EForm[] findByF_GID_SC_PrevAndNext(long eFormId, long groupId,
		String serviceCode,
		com.liferay.portal.kernel.util.OrderByComparator<EForm> orderByComparator)
		throws NoSuchEFormException;

	/**
	* Removes all the e forms where groupId = &#63; and serviceCode = &#63; from the database.
	*
	* @param groupId the group ID
	* @param serviceCode the service code
	*/
	public void removeByF_GID_SC(long groupId, String serviceCode);

	/**
	* Returns the number of e forms where groupId = &#63; and serviceCode = &#63;.
	*
	* @param groupId the group ID
	* @param serviceCode the service code
	* @return the number of matching e forms
	*/
	public int countByF_GID_SC(long groupId, String serviceCode);

	/**
	* Returns the e form where groupId = &#63; and eFormNo = &#63; or throws a {@link NoSuchEFormException} if it could not be found.
	*
	* @param groupId the group ID
	* @param eFormNo the e form no
	* @return the matching e form
	* @throws NoSuchEFormException if a matching e form could not be found
	*/
	public EForm findByF_GID_FORM(long groupId, String eFormNo)
		throws NoSuchEFormException;

	/**
	* Returns the e form where groupId = &#63; and eFormNo = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param eFormNo the e form no
	* @return the matching e form, or <code>null</code> if a matching e form could not be found
	*/
	public EForm fetchByF_GID_FORM(long groupId, String eFormNo);

	/**
	* Returns the e form where groupId = &#63; and eFormNo = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param eFormNo the e form no
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching e form, or <code>null</code> if a matching e form could not be found
	*/
	public EForm fetchByF_GID_FORM(long groupId, String eFormNo,
		boolean retrieveFromCache);

	/**
	* Removes the e form where groupId = &#63; and eFormNo = &#63; from the database.
	*
	* @param groupId the group ID
	* @param eFormNo the e form no
	* @return the e form that was removed
	*/
	public EForm removeByF_GID_FORM(long groupId, String eFormNo)
		throws NoSuchEFormException;

	/**
	* Returns the number of e forms where groupId = &#63; and eFormNo = &#63;.
	*
	* @param groupId the group ID
	* @param eFormNo the e form no
	* @return the number of matching e forms
	*/
	public int countByF_GID_FORM(long groupId, String eFormNo);

	/**
	* Caches the e form in the entity cache if it is enabled.
	*
	* @param eForm the e form
	*/
	public void cacheResult(EForm eForm);

	/**
	* Caches the e forms in the entity cache if it is enabled.
	*
	* @param eForms the e forms
	*/
	public void cacheResult(java.util.List<EForm> eForms);

	/**
	* Creates a new e form with the primary key. Does not add the e form to the database.
	*
	* @param eFormId the primary key for the new e form
	* @return the new e form
	*/
	public EForm create(long eFormId);

	/**
	* Removes the e form with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param eFormId the primary key of the e form
	* @return the e form that was removed
	* @throws NoSuchEFormException if a e form with the primary key could not be found
	*/
	public EForm remove(long eFormId) throws NoSuchEFormException;

	public EForm updateImpl(EForm eForm);

	/**
	* Returns the e form with the primary key or throws a {@link NoSuchEFormException} if it could not be found.
	*
	* @param eFormId the primary key of the e form
	* @return the e form
	* @throws NoSuchEFormException if a e form with the primary key could not be found
	*/
	public EForm findByPrimaryKey(long eFormId) throws NoSuchEFormException;

	/**
	* Returns the e form with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param eFormId the primary key of the e form
	* @return the e form, or <code>null</code> if a e form with the primary key could not be found
	*/
	public EForm fetchByPrimaryKey(long eFormId);

	@Override
	public java.util.Map<java.io.Serializable, EForm> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the e forms.
	*
	* @return the e forms
	*/
	public java.util.List<EForm> findAll();

	/**
	* Returns a range of all the e forms.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EFormModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of e forms
	* @param end the upper bound of the range of e forms (not inclusive)
	* @return the range of e forms
	*/
	public java.util.List<EForm> findAll(int start, int end);

	/**
	* Returns an ordered range of all the e forms.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EFormModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of e forms
	* @param end the upper bound of the range of e forms (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of e forms
	*/
	public java.util.List<EForm> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<EForm> orderByComparator);

	/**
	* Returns an ordered range of all the e forms.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EFormModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of e forms
	* @param end the upper bound of the range of e forms (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of e forms
	*/
	public java.util.List<EForm> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<EForm> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the e forms from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of e forms.
	*
	* @return the number of e forms
	*/
	public int countAll();

	@Override
	public java.util.Set<String> getBadColumnNames();
}