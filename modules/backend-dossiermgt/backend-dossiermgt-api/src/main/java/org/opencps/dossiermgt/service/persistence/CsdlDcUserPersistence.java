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

import org.opencps.dossiermgt.exception.NoSuchCsdlDcUserException;
import org.opencps.dossiermgt.model.CsdlDcUser;

/**
 * The persistence interface for the csdl dc user service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author huymq
 * @see org.opencps.dossiermgt.service.persistence.impl.CsdlDcUserPersistenceImpl
 * @see CsdlDcUserUtil
 * @generated
 */
@ProviderType
public interface CsdlDcUserPersistence extends BasePersistence<CsdlDcUser> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CsdlDcUserUtil} to access the csdl dc user persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the csdl dc users where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching csdl dc users
	*/
	public java.util.List<CsdlDcUser> findByUuid(String uuid);

	/**
	* Returns a range of all the csdl dc users where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CsdlDcUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of csdl dc users
	* @param end the upper bound of the range of csdl dc users (not inclusive)
	* @return the range of matching csdl dc users
	*/
	public java.util.List<CsdlDcUser> findByUuid(String uuid, int start, int end);

	/**
	* Returns an ordered range of all the csdl dc users where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CsdlDcUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of csdl dc users
	* @param end the upper bound of the range of csdl dc users (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching csdl dc users
	*/
	public java.util.List<CsdlDcUser> findByUuid(String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<CsdlDcUser> orderByComparator);

	/**
	* Returns an ordered range of all the csdl dc users where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CsdlDcUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of csdl dc users
	* @param end the upper bound of the range of csdl dc users (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching csdl dc users
	*/
	public java.util.List<CsdlDcUser> findByUuid(String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<CsdlDcUser> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first csdl dc user in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching csdl dc user
	* @throws NoSuchCsdlDcUserException if a matching csdl dc user could not be found
	*/
	public CsdlDcUser findByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<CsdlDcUser> orderByComparator)
		throws NoSuchCsdlDcUserException;

	/**
	* Returns the first csdl dc user in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching csdl dc user, or <code>null</code> if a matching csdl dc user could not be found
	*/
	public CsdlDcUser fetchByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<CsdlDcUser> orderByComparator);

	/**
	* Returns the last csdl dc user in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching csdl dc user
	* @throws NoSuchCsdlDcUserException if a matching csdl dc user could not be found
	*/
	public CsdlDcUser findByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<CsdlDcUser> orderByComparator)
		throws NoSuchCsdlDcUserException;

	/**
	* Returns the last csdl dc user in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching csdl dc user, or <code>null</code> if a matching csdl dc user could not be found
	*/
	public CsdlDcUser fetchByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<CsdlDcUser> orderByComparator);

	/**
	* Returns the csdl dc users before and after the current csdl dc user in the ordered set where uuid = &#63;.
	*
	* @param idDcUser the primary key of the current csdl dc user
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next csdl dc user
	* @throws NoSuchCsdlDcUserException if a csdl dc user with the primary key could not be found
	*/
	public CsdlDcUser[] findByUuid_PrevAndNext(long idDcUser, String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<CsdlDcUser> orderByComparator)
		throws NoSuchCsdlDcUserException;

	/**
	* Removes all the csdl dc users where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(String uuid);

	/**
	* Returns the number of csdl dc users where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching csdl dc users
	*/
	public int countByUuid(String uuid);

	/**
	* Returns the csdl dc user where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchCsdlDcUserException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching csdl dc user
	* @throws NoSuchCsdlDcUserException if a matching csdl dc user could not be found
	*/
	public CsdlDcUser findByUUID_G(String uuid, long groupId)
		throws NoSuchCsdlDcUserException;

	/**
	* Returns the csdl dc user where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching csdl dc user, or <code>null</code> if a matching csdl dc user could not be found
	*/
	public CsdlDcUser fetchByUUID_G(String uuid, long groupId);

	/**
	* Returns the csdl dc user where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching csdl dc user, or <code>null</code> if a matching csdl dc user could not be found
	*/
	public CsdlDcUser fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the csdl dc user where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the csdl dc user that was removed
	*/
	public CsdlDcUser removeByUUID_G(String uuid, long groupId)
		throws NoSuchCsdlDcUserException;

	/**
	* Returns the number of csdl dc users where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching csdl dc users
	*/
	public int countByUUID_G(String uuid, long groupId);

	/**
	* Returns all the csdl dc users where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching csdl dc users
	*/
	public java.util.List<CsdlDcUser> findByUuid_C(String uuid, long companyId);

	/**
	* Returns a range of all the csdl dc users where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CsdlDcUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of csdl dc users
	* @param end the upper bound of the range of csdl dc users (not inclusive)
	* @return the range of matching csdl dc users
	*/
	public java.util.List<CsdlDcUser> findByUuid_C(String uuid, long companyId,
		int start, int end);

	/**
	* Returns an ordered range of all the csdl dc users where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CsdlDcUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of csdl dc users
	* @param end the upper bound of the range of csdl dc users (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching csdl dc users
	*/
	public java.util.List<CsdlDcUser> findByUuid_C(String uuid, long companyId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CsdlDcUser> orderByComparator);

	/**
	* Returns an ordered range of all the csdl dc users where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CsdlDcUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of csdl dc users
	* @param end the upper bound of the range of csdl dc users (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching csdl dc users
	*/
	public java.util.List<CsdlDcUser> findByUuid_C(String uuid, long companyId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CsdlDcUser> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first csdl dc user in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching csdl dc user
	* @throws NoSuchCsdlDcUserException if a matching csdl dc user could not be found
	*/
	public CsdlDcUser findByUuid_C_First(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<CsdlDcUser> orderByComparator)
		throws NoSuchCsdlDcUserException;

	/**
	* Returns the first csdl dc user in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching csdl dc user, or <code>null</code> if a matching csdl dc user could not be found
	*/
	public CsdlDcUser fetchByUuid_C_First(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<CsdlDcUser> orderByComparator);

	/**
	* Returns the last csdl dc user in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching csdl dc user
	* @throws NoSuchCsdlDcUserException if a matching csdl dc user could not be found
	*/
	public CsdlDcUser findByUuid_C_Last(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<CsdlDcUser> orderByComparator)
		throws NoSuchCsdlDcUserException;

	/**
	* Returns the last csdl dc user in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching csdl dc user, or <code>null</code> if a matching csdl dc user could not be found
	*/
	public CsdlDcUser fetchByUuid_C_Last(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<CsdlDcUser> orderByComparator);

	/**
	* Returns the csdl dc users before and after the current csdl dc user in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param idDcUser the primary key of the current csdl dc user
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next csdl dc user
	* @throws NoSuchCsdlDcUserException if a csdl dc user with the primary key could not be found
	*/
	public CsdlDcUser[] findByUuid_C_PrevAndNext(long idDcUser, String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<CsdlDcUser> orderByComparator)
		throws NoSuchCsdlDcUserException;

	/**
	* Removes all the csdl dc users where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(String uuid, long companyId);

	/**
	* Returns the number of csdl dc users where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching csdl dc users
	*/
	public int countByUuid_C(String uuid, long companyId);

	/**
	* Returns the csdl dc user where govAgencyCode = &#63; and employeeEmail = &#63; and status = &#63; or throws a {@link NoSuchCsdlDcUserException} if it could not be found.
	*
	* @param govAgencyCode the gov agency code
	* @param employeeEmail the employee email
	* @param status the status
	* @return the matching csdl dc user
	* @throws NoSuchCsdlDcUserException if a matching csdl dc user could not be found
	*/
	public CsdlDcUser findByF_GOV_EMAIL_STATUS(String govAgencyCode,
		String employeeEmail, int status) throws NoSuchCsdlDcUserException;

	/**
	* Returns the csdl dc user where govAgencyCode = &#63; and employeeEmail = &#63; and status = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param govAgencyCode the gov agency code
	* @param employeeEmail the employee email
	* @param status the status
	* @return the matching csdl dc user, or <code>null</code> if a matching csdl dc user could not be found
	*/
	public CsdlDcUser fetchByF_GOV_EMAIL_STATUS(String govAgencyCode,
		String employeeEmail, int status);

	/**
	* Returns the csdl dc user where govAgencyCode = &#63; and employeeEmail = &#63; and status = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param govAgencyCode the gov agency code
	* @param employeeEmail the employee email
	* @param status the status
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching csdl dc user, or <code>null</code> if a matching csdl dc user could not be found
	*/
	public CsdlDcUser fetchByF_GOV_EMAIL_STATUS(String govAgencyCode,
		String employeeEmail, int status, boolean retrieveFromCache);

	/**
	* Removes the csdl dc user where govAgencyCode = &#63; and employeeEmail = &#63; and status = &#63; from the database.
	*
	* @param govAgencyCode the gov agency code
	* @param employeeEmail the employee email
	* @param status the status
	* @return the csdl dc user that was removed
	*/
	public CsdlDcUser removeByF_GOV_EMAIL_STATUS(String govAgencyCode,
		String employeeEmail, int status) throws NoSuchCsdlDcUserException;

	/**
	* Returns the number of csdl dc users where govAgencyCode = &#63; and employeeEmail = &#63; and status = &#63;.
	*
	* @param govAgencyCode the gov agency code
	* @param employeeEmail the employee email
	* @param status the status
	* @return the number of matching csdl dc users
	*/
	public int countByF_GOV_EMAIL_STATUS(String govAgencyCode,
		String employeeEmail, int status);

	/**
	* Caches the csdl dc user in the entity cache if it is enabled.
	*
	* @param csdlDcUser the csdl dc user
	*/
	public void cacheResult(CsdlDcUser csdlDcUser);

	/**
	* Caches the csdl dc users in the entity cache if it is enabled.
	*
	* @param csdlDcUsers the csdl dc users
	*/
	public void cacheResult(java.util.List<CsdlDcUser> csdlDcUsers);

	/**
	* Creates a new csdl dc user with the primary key. Does not add the csdl dc user to the database.
	*
	* @param idDcUser the primary key for the new csdl dc user
	* @return the new csdl dc user
	*/
	public CsdlDcUser create(long idDcUser);

	/**
	* Removes the csdl dc user with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param idDcUser the primary key of the csdl dc user
	* @return the csdl dc user that was removed
	* @throws NoSuchCsdlDcUserException if a csdl dc user with the primary key could not be found
	*/
	public CsdlDcUser remove(long idDcUser) throws NoSuchCsdlDcUserException;

	public CsdlDcUser updateImpl(CsdlDcUser csdlDcUser);

	/**
	* Returns the csdl dc user with the primary key or throws a {@link NoSuchCsdlDcUserException} if it could not be found.
	*
	* @param idDcUser the primary key of the csdl dc user
	* @return the csdl dc user
	* @throws NoSuchCsdlDcUserException if a csdl dc user with the primary key could not be found
	*/
	public CsdlDcUser findByPrimaryKey(long idDcUser)
		throws NoSuchCsdlDcUserException;

	/**
	* Returns the csdl dc user with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param idDcUser the primary key of the csdl dc user
	* @return the csdl dc user, or <code>null</code> if a csdl dc user with the primary key could not be found
	*/
	public CsdlDcUser fetchByPrimaryKey(long idDcUser);

	@Override
	public java.util.Map<java.io.Serializable, CsdlDcUser> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the csdl dc users.
	*
	* @return the csdl dc users
	*/
	public java.util.List<CsdlDcUser> findAll();

	/**
	* Returns a range of all the csdl dc users.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CsdlDcUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of csdl dc users
	* @param end the upper bound of the range of csdl dc users (not inclusive)
	* @return the range of csdl dc users
	*/
	public java.util.List<CsdlDcUser> findAll(int start, int end);

	/**
	* Returns an ordered range of all the csdl dc users.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CsdlDcUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of csdl dc users
	* @param end the upper bound of the range of csdl dc users (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of csdl dc users
	*/
	public java.util.List<CsdlDcUser> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CsdlDcUser> orderByComparator);

	/**
	* Returns an ordered range of all the csdl dc users.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CsdlDcUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of csdl dc users
	* @param end the upper bound of the range of csdl dc users (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of csdl dc users
	*/
	public java.util.List<CsdlDcUser> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CsdlDcUser> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the csdl dc users from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of csdl dc users.
	*
	* @return the number of csdl dc users
	*/
	public int countAll();

	@Override
	public java.util.Set<String> getBadColumnNames();
}