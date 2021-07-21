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

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.opencps.dossiermgt.model.CsdlDcUser;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the csdl dc user service. This utility wraps {@link org.opencps.dossiermgt.service.persistence.impl.CsdlDcUserPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author huymq
 * @see CsdlDcUserPersistence
 * @see org.opencps.dossiermgt.service.persistence.impl.CsdlDcUserPersistenceImpl
 * @generated
 */
@ProviderType
public class CsdlDcUserUtil {
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
	public static void clearCache(CsdlDcUser csdlDcUser) {
		getPersistence().clearCache(csdlDcUser);
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
	public static List<CsdlDcUser> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<CsdlDcUser> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<CsdlDcUser> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<CsdlDcUser> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static CsdlDcUser update(CsdlDcUser csdlDcUser) {
		return getPersistence().update(csdlDcUser);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static CsdlDcUser update(CsdlDcUser csdlDcUser,
		ServiceContext serviceContext) {
		return getPersistence().update(csdlDcUser, serviceContext);
	}

	/**
	* Returns all the csdl dc users where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching csdl dc users
	*/
	public static List<CsdlDcUser> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

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
	public static List<CsdlDcUser> findByUuid(String uuid, int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

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
	public static List<CsdlDcUser> findByUuid(String uuid, int start, int end,
		OrderByComparator<CsdlDcUser> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

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
	public static List<CsdlDcUser> findByUuid(String uuid, int start, int end,
		OrderByComparator<CsdlDcUser> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first csdl dc user in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching csdl dc user
	* @throws NoSuchCsdlDcUserException if a matching csdl dc user could not be found
	*/
	public static CsdlDcUser findByUuid_First(String uuid,
		OrderByComparator<CsdlDcUser> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchCsdlDcUserException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first csdl dc user in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching csdl dc user, or <code>null</code> if a matching csdl dc user could not be found
	*/
	public static CsdlDcUser fetchByUuid_First(String uuid,
		OrderByComparator<CsdlDcUser> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last csdl dc user in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching csdl dc user
	* @throws NoSuchCsdlDcUserException if a matching csdl dc user could not be found
	*/
	public static CsdlDcUser findByUuid_Last(String uuid,
		OrderByComparator<CsdlDcUser> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchCsdlDcUserException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last csdl dc user in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching csdl dc user, or <code>null</code> if a matching csdl dc user could not be found
	*/
	public static CsdlDcUser fetchByUuid_Last(String uuid,
		OrderByComparator<CsdlDcUser> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the csdl dc users before and after the current csdl dc user in the ordered set where uuid = &#63;.
	*
	* @param idDcUser the primary key of the current csdl dc user
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next csdl dc user
	* @throws NoSuchCsdlDcUserException if a csdl dc user with the primary key could not be found
	*/
	public static CsdlDcUser[] findByUuid_PrevAndNext(long idDcUser,
		String uuid, OrderByComparator<CsdlDcUser> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchCsdlDcUserException {
		return getPersistence()
				   .findByUuid_PrevAndNext(idDcUser, uuid, orderByComparator);
	}

	/**
	* Removes all the csdl dc users where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of csdl dc users where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching csdl dc users
	*/
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the csdl dc user where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchCsdlDcUserException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching csdl dc user
	* @throws NoSuchCsdlDcUserException if a matching csdl dc user could not be found
	*/
	public static CsdlDcUser findByUUID_G(String uuid, long groupId)
		throws org.opencps.dossiermgt.exception.NoSuchCsdlDcUserException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the csdl dc user where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching csdl dc user, or <code>null</code> if a matching csdl dc user could not be found
	*/
	public static CsdlDcUser fetchByUUID_G(String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the csdl dc user where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching csdl dc user, or <code>null</code> if a matching csdl dc user could not be found
	*/
	public static CsdlDcUser fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the csdl dc user where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the csdl dc user that was removed
	*/
	public static CsdlDcUser removeByUUID_G(String uuid, long groupId)
		throws org.opencps.dossiermgt.exception.NoSuchCsdlDcUserException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of csdl dc users where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching csdl dc users
	*/
	public static int countByUUID_G(String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the csdl dc users where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching csdl dc users
	*/
	public static List<CsdlDcUser> findByUuid_C(String uuid, long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

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
	public static List<CsdlDcUser> findByUuid_C(String uuid, long companyId,
		int start, int end) {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

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
	public static List<CsdlDcUser> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<CsdlDcUser> orderByComparator) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

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
	public static List<CsdlDcUser> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<CsdlDcUser> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first csdl dc user in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching csdl dc user
	* @throws NoSuchCsdlDcUserException if a matching csdl dc user could not be found
	*/
	public static CsdlDcUser findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<CsdlDcUser> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchCsdlDcUserException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first csdl dc user in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching csdl dc user, or <code>null</code> if a matching csdl dc user could not be found
	*/
	public static CsdlDcUser fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<CsdlDcUser> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last csdl dc user in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching csdl dc user
	* @throws NoSuchCsdlDcUserException if a matching csdl dc user could not be found
	*/
	public static CsdlDcUser findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<CsdlDcUser> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchCsdlDcUserException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last csdl dc user in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching csdl dc user, or <code>null</code> if a matching csdl dc user could not be found
	*/
	public static CsdlDcUser fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<CsdlDcUser> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

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
	public static CsdlDcUser[] findByUuid_C_PrevAndNext(long idDcUser,
		String uuid, long companyId,
		OrderByComparator<CsdlDcUser> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchCsdlDcUserException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(idDcUser, uuid, companyId,
			orderByComparator);
	}

	/**
	* Removes all the csdl dc users where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public static void removeByUuid_C(String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of csdl dc users where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching csdl dc users
	*/
	public static int countByUuid_C(String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Returns the csdl dc user where govAgencyCode = &#63; and employeeEmail = &#63; and status = &#63; or throws a {@link NoSuchCsdlDcUserException} if it could not be found.
	*
	* @param govAgencyCode the gov agency code
	* @param employeeEmail the employee email
	* @param status the status
	* @return the matching csdl dc user
	* @throws NoSuchCsdlDcUserException if a matching csdl dc user could not be found
	*/
	public static CsdlDcUser findByF_GOV_EMAIL_STATUS(String govAgencyCode,
		String employeeEmail, int status)
		throws org.opencps.dossiermgt.exception.NoSuchCsdlDcUserException {
		return getPersistence()
				   .findByF_GOV_EMAIL_STATUS(govAgencyCode, employeeEmail,
			status);
	}

	/**
	* Returns the csdl dc user where govAgencyCode = &#63; and employeeEmail = &#63; and status = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param govAgencyCode the gov agency code
	* @param employeeEmail the employee email
	* @param status the status
	* @return the matching csdl dc user, or <code>null</code> if a matching csdl dc user could not be found
	*/
	public static CsdlDcUser fetchByF_GOV_EMAIL_STATUS(String govAgencyCode,
		String employeeEmail, int status) {
		return getPersistence()
				   .fetchByF_GOV_EMAIL_STATUS(govAgencyCode, employeeEmail,
			status);
	}

	/**
	* Returns the csdl dc user where govAgencyCode = &#63; and employeeEmail = &#63; and status = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param govAgencyCode the gov agency code
	* @param employeeEmail the employee email
	* @param status the status
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching csdl dc user, or <code>null</code> if a matching csdl dc user could not be found
	*/
	public static CsdlDcUser fetchByF_GOV_EMAIL_STATUS(String govAgencyCode,
		String employeeEmail, int status, boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByF_GOV_EMAIL_STATUS(govAgencyCode, employeeEmail,
			status, retrieveFromCache);
	}

	/**
	* Removes the csdl dc user where govAgencyCode = &#63; and employeeEmail = &#63; and status = &#63; from the database.
	*
	* @param govAgencyCode the gov agency code
	* @param employeeEmail the employee email
	* @param status the status
	* @return the csdl dc user that was removed
	*/
	public static CsdlDcUser removeByF_GOV_EMAIL_STATUS(String govAgencyCode,
		String employeeEmail, int status)
		throws org.opencps.dossiermgt.exception.NoSuchCsdlDcUserException {
		return getPersistence()
				   .removeByF_GOV_EMAIL_STATUS(govAgencyCode, employeeEmail,
			status);
	}

	/**
	* Returns the number of csdl dc users where govAgencyCode = &#63; and employeeEmail = &#63; and status = &#63;.
	*
	* @param govAgencyCode the gov agency code
	* @param employeeEmail the employee email
	* @param status the status
	* @return the number of matching csdl dc users
	*/
	public static int countByF_GOV_EMAIL_STATUS(String govAgencyCode,
		String employeeEmail, int status) {
		return getPersistence()
				   .countByF_GOV_EMAIL_STATUS(govAgencyCode, employeeEmail,
			status);
	}

	/**
	* Caches the csdl dc user in the entity cache if it is enabled.
	*
	* @param csdlDcUser the csdl dc user
	*/
	public static void cacheResult(CsdlDcUser csdlDcUser) {
		getPersistence().cacheResult(csdlDcUser);
	}

	/**
	* Caches the csdl dc users in the entity cache if it is enabled.
	*
	* @param csdlDcUsers the csdl dc users
	*/
	public static void cacheResult(List<CsdlDcUser> csdlDcUsers) {
		getPersistence().cacheResult(csdlDcUsers);
	}

	/**
	* Creates a new csdl dc user with the primary key. Does not add the csdl dc user to the database.
	*
	* @param idDcUser the primary key for the new csdl dc user
	* @return the new csdl dc user
	*/
	public static CsdlDcUser create(long idDcUser) {
		return getPersistence().create(idDcUser);
	}

	/**
	* Removes the csdl dc user with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param idDcUser the primary key of the csdl dc user
	* @return the csdl dc user that was removed
	* @throws NoSuchCsdlDcUserException if a csdl dc user with the primary key could not be found
	*/
	public static CsdlDcUser remove(long idDcUser)
		throws org.opencps.dossiermgt.exception.NoSuchCsdlDcUserException {
		return getPersistence().remove(idDcUser);
	}

	public static CsdlDcUser updateImpl(CsdlDcUser csdlDcUser) {
		return getPersistence().updateImpl(csdlDcUser);
	}

	/**
	* Returns the csdl dc user with the primary key or throws a {@link NoSuchCsdlDcUserException} if it could not be found.
	*
	* @param idDcUser the primary key of the csdl dc user
	* @return the csdl dc user
	* @throws NoSuchCsdlDcUserException if a csdl dc user with the primary key could not be found
	*/
	public static CsdlDcUser findByPrimaryKey(long idDcUser)
		throws org.opencps.dossiermgt.exception.NoSuchCsdlDcUserException {
		return getPersistence().findByPrimaryKey(idDcUser);
	}

	/**
	* Returns the csdl dc user with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param idDcUser the primary key of the csdl dc user
	* @return the csdl dc user, or <code>null</code> if a csdl dc user with the primary key could not be found
	*/
	public static CsdlDcUser fetchByPrimaryKey(long idDcUser) {
		return getPersistence().fetchByPrimaryKey(idDcUser);
	}

	public static java.util.Map<java.io.Serializable, CsdlDcUser> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the csdl dc users.
	*
	* @return the csdl dc users
	*/
	public static List<CsdlDcUser> findAll() {
		return getPersistence().findAll();
	}

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
	public static List<CsdlDcUser> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

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
	public static List<CsdlDcUser> findAll(int start, int end,
		OrderByComparator<CsdlDcUser> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

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
	public static List<CsdlDcUser> findAll(int start, int end,
		OrderByComparator<CsdlDcUser> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the csdl dc users from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of csdl dc users.
	*
	* @return the number of csdl dc users
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static CsdlDcUserPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<CsdlDcUserPersistence, CsdlDcUserPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(CsdlDcUserPersistence.class);

		ServiceTracker<CsdlDcUserPersistence, CsdlDcUserPersistence> serviceTracker =
			new ServiceTracker<CsdlDcUserPersistence, CsdlDcUserPersistence>(bundle.getBundleContext(),
				CsdlDcUserPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}