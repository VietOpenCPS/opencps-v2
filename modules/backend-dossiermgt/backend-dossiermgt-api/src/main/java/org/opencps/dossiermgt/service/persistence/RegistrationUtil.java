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

import org.opencps.dossiermgt.model.Registration;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the registration service. This utility wraps {@link org.opencps.dossiermgt.service.persistence.impl.RegistrationPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author huymq
 * @see RegistrationPersistence
 * @see org.opencps.dossiermgt.service.persistence.impl.RegistrationPersistenceImpl
 * @generated
 */
@ProviderType
public class RegistrationUtil {
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
	public static void clearCache(Registration registration) {
		getPersistence().clearCache(registration);
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
	public static List<Registration> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Registration> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Registration> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<Registration> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static Registration update(Registration registration) {
		return getPersistence().update(registration);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static Registration update(Registration registration,
		ServiceContext serviceContext) {
		return getPersistence().update(registration, serviceContext);
	}

	/**
	* Returns all the registrations where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching registrations
	*/
	public static List<Registration> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

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
	public static List<Registration> findByUuid(String uuid, int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

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
	public static List<Registration> findByUuid(String uuid, int start,
		int end, OrderByComparator<Registration> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

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
	public static List<Registration> findByUuid(String uuid, int start,
		int end, OrderByComparator<Registration> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first registration in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching registration
	* @throws NoSuchRegistrationException if a matching registration could not be found
	*/
	public static Registration findByUuid_First(String uuid,
		OrderByComparator<Registration> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchRegistrationException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first registration in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching registration, or <code>null</code> if a matching registration could not be found
	*/
	public static Registration fetchByUuid_First(String uuid,
		OrderByComparator<Registration> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last registration in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching registration
	* @throws NoSuchRegistrationException if a matching registration could not be found
	*/
	public static Registration findByUuid_Last(String uuid,
		OrderByComparator<Registration> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchRegistrationException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last registration in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching registration, or <code>null</code> if a matching registration could not be found
	*/
	public static Registration fetchByUuid_Last(String uuid,
		OrderByComparator<Registration> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the registrations before and after the current registration in the ordered set where uuid = &#63;.
	*
	* @param registrationId the primary key of the current registration
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next registration
	* @throws NoSuchRegistrationException if a registration with the primary key could not be found
	*/
	public static Registration[] findByUuid_PrevAndNext(long registrationId,
		String uuid, OrderByComparator<Registration> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchRegistrationException {
		return getPersistence()
				   .findByUuid_PrevAndNext(registrationId, uuid,
			orderByComparator);
	}

	/**
	* Removes all the registrations where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of registrations where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching registrations
	*/
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the registration where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchRegistrationException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching registration
	* @throws NoSuchRegistrationException if a matching registration could not be found
	*/
	public static Registration findByUUID_G(String uuid, long groupId)
		throws org.opencps.dossiermgt.exception.NoSuchRegistrationException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the registration where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching registration, or <code>null</code> if a matching registration could not be found
	*/
	public static Registration fetchByUUID_G(String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the registration where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching registration, or <code>null</code> if a matching registration could not be found
	*/
	public static Registration fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the registration where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the registration that was removed
	*/
	public static Registration removeByUUID_G(String uuid, long groupId)
		throws org.opencps.dossiermgt.exception.NoSuchRegistrationException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of registrations where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching registrations
	*/
	public static int countByUUID_G(String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the registrations where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching registrations
	*/
	public static List<Registration> findByUuid_C(String uuid, long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

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
	public static List<Registration> findByUuid_C(String uuid, long companyId,
		int start, int end) {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

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
	public static List<Registration> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<Registration> orderByComparator) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

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
	public static List<Registration> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<Registration> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first registration in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching registration
	* @throws NoSuchRegistrationException if a matching registration could not be found
	*/
	public static Registration findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<Registration> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchRegistrationException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first registration in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching registration, or <code>null</code> if a matching registration could not be found
	*/
	public static Registration fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<Registration> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last registration in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching registration
	* @throws NoSuchRegistrationException if a matching registration could not be found
	*/
	public static Registration findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<Registration> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchRegistrationException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last registration in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching registration, or <code>null</code> if a matching registration could not be found
	*/
	public static Registration fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<Registration> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

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
	public static Registration[] findByUuid_C_PrevAndNext(long registrationId,
		String uuid, long companyId,
		OrderByComparator<Registration> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchRegistrationException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(registrationId, uuid, companyId,
			orderByComparator);
	}

	/**
	* Removes all the registrations where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public static void removeByUuid_C(String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of registrations where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching registrations
	*/
	public static int countByUuid_C(String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Returns all the registrations where groupId = &#63; and submitting = &#63;.
	*
	* @param groupId the group ID
	* @param submitting the submitting
	* @return the matching registrations
	*/
	public static List<Registration> findByF_submitting(long groupId,
		boolean submitting) {
		return getPersistence().findByF_submitting(groupId, submitting);
	}

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
	public static List<Registration> findByF_submitting(long groupId,
		boolean submitting, int start, int end) {
		return getPersistence()
				   .findByF_submitting(groupId, submitting, start, end);
	}

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
	public static List<Registration> findByF_submitting(long groupId,
		boolean submitting, int start, int end,
		OrderByComparator<Registration> orderByComparator) {
		return getPersistence()
				   .findByF_submitting(groupId, submitting, start, end,
			orderByComparator);
	}

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
	public static List<Registration> findByF_submitting(long groupId,
		boolean submitting, int start, int end,
		OrderByComparator<Registration> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByF_submitting(groupId, submitting, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first registration in the ordered set where groupId = &#63; and submitting = &#63;.
	*
	* @param groupId the group ID
	* @param submitting the submitting
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching registration
	* @throws NoSuchRegistrationException if a matching registration could not be found
	*/
	public static Registration findByF_submitting_First(long groupId,
		boolean submitting, OrderByComparator<Registration> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchRegistrationException {
		return getPersistence()
				   .findByF_submitting_First(groupId, submitting,
			orderByComparator);
	}

	/**
	* Returns the first registration in the ordered set where groupId = &#63; and submitting = &#63;.
	*
	* @param groupId the group ID
	* @param submitting the submitting
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching registration, or <code>null</code> if a matching registration could not be found
	*/
	public static Registration fetchByF_submitting_First(long groupId,
		boolean submitting, OrderByComparator<Registration> orderByComparator) {
		return getPersistence()
				   .fetchByF_submitting_First(groupId, submitting,
			orderByComparator);
	}

	/**
	* Returns the last registration in the ordered set where groupId = &#63; and submitting = &#63;.
	*
	* @param groupId the group ID
	* @param submitting the submitting
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching registration
	* @throws NoSuchRegistrationException if a matching registration could not be found
	*/
	public static Registration findByF_submitting_Last(long groupId,
		boolean submitting, OrderByComparator<Registration> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchRegistrationException {
		return getPersistence()
				   .findByF_submitting_Last(groupId, submitting,
			orderByComparator);
	}

	/**
	* Returns the last registration in the ordered set where groupId = &#63; and submitting = &#63;.
	*
	* @param groupId the group ID
	* @param submitting the submitting
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching registration, or <code>null</code> if a matching registration could not be found
	*/
	public static Registration fetchByF_submitting_Last(long groupId,
		boolean submitting, OrderByComparator<Registration> orderByComparator) {
		return getPersistence()
				   .fetchByF_submitting_Last(groupId, submitting,
			orderByComparator);
	}

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
	public static Registration[] findByF_submitting_PrevAndNext(
		long registrationId, long groupId, boolean submitting,
		OrderByComparator<Registration> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchRegistrationException {
		return getPersistence()
				   .findByF_submitting_PrevAndNext(registrationId, groupId,
			submitting, orderByComparator);
	}

	/**
	* Removes all the registrations where groupId = &#63; and submitting = &#63; from the database.
	*
	* @param groupId the group ID
	* @param submitting the submitting
	*/
	public static void removeByF_submitting(long groupId, boolean submitting) {
		getPersistence().removeByF_submitting(groupId, submitting);
	}

	/**
	* Returns the number of registrations where groupId = &#63; and submitting = &#63;.
	*
	* @param groupId the group ID
	* @param submitting the submitting
	* @return the number of matching registrations
	*/
	public static int countByF_submitting(long groupId, boolean submitting) {
		return getPersistence().countByF_submitting(groupId, submitting);
	}

	/**
	* Returns all the registrations where groupId = &#63; and userId = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @return the matching registrations
	*/
	public static List<Registration> findByGID_UID(long groupId, long userId) {
		return getPersistence().findByGID_UID(groupId, userId);
	}

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
	public static List<Registration> findByGID_UID(long groupId, long userId,
		int start, int end) {
		return getPersistence().findByGID_UID(groupId, userId, start, end);
	}

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
	public static List<Registration> findByGID_UID(long groupId, long userId,
		int start, int end, OrderByComparator<Registration> orderByComparator) {
		return getPersistence()
				   .findByGID_UID(groupId, userId, start, end, orderByComparator);
	}

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
	public static List<Registration> findByGID_UID(long groupId, long userId,
		int start, int end, OrderByComparator<Registration> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByGID_UID(groupId, userId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first registration in the ordered set where groupId = &#63; and userId = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching registration
	* @throws NoSuchRegistrationException if a matching registration could not be found
	*/
	public static Registration findByGID_UID_First(long groupId, long userId,
		OrderByComparator<Registration> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchRegistrationException {
		return getPersistence()
				   .findByGID_UID_First(groupId, userId, orderByComparator);
	}

	/**
	* Returns the first registration in the ordered set where groupId = &#63; and userId = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching registration, or <code>null</code> if a matching registration could not be found
	*/
	public static Registration fetchByGID_UID_First(long groupId, long userId,
		OrderByComparator<Registration> orderByComparator) {
		return getPersistence()
				   .fetchByGID_UID_First(groupId, userId, orderByComparator);
	}

	/**
	* Returns the last registration in the ordered set where groupId = &#63; and userId = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching registration
	* @throws NoSuchRegistrationException if a matching registration could not be found
	*/
	public static Registration findByGID_UID_Last(long groupId, long userId,
		OrderByComparator<Registration> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchRegistrationException {
		return getPersistence()
				   .findByGID_UID_Last(groupId, userId, orderByComparator);
	}

	/**
	* Returns the last registration in the ordered set where groupId = &#63; and userId = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching registration, or <code>null</code> if a matching registration could not be found
	*/
	public static Registration fetchByGID_UID_Last(long groupId, long userId,
		OrderByComparator<Registration> orderByComparator) {
		return getPersistence()
				   .fetchByGID_UID_Last(groupId, userId, orderByComparator);
	}

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
	public static Registration[] findByGID_UID_PrevAndNext(
		long registrationId, long groupId, long userId,
		OrderByComparator<Registration> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchRegistrationException {
		return getPersistence()
				   .findByGID_UID_PrevAndNext(registrationId, groupId, userId,
			orderByComparator);
	}

	/**
	* Removes all the registrations where groupId = &#63; and userId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param userId the user ID
	*/
	public static void removeByGID_UID(long groupId, long userId) {
		getPersistence().removeByGID_UID(groupId, userId);
	}

	/**
	* Returns the number of registrations where groupId = &#63; and userId = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @return the number of matching registrations
	*/
	public static int countByGID_UID(long groupId, long userId) {
		return getPersistence().countByGID_UID(groupId, userId);
	}

	/**
	* Returns the registration where groupId = &#63; and registrationId = &#63; or throws a {@link NoSuchRegistrationException} if it could not be found.
	*
	* @param groupId the group ID
	* @param registrationId the registration ID
	* @return the matching registration
	* @throws NoSuchRegistrationException if a matching registration could not be found
	*/
	public static Registration findByG_REGID(long groupId, long registrationId)
		throws org.opencps.dossiermgt.exception.NoSuchRegistrationException {
		return getPersistence().findByG_REGID(groupId, registrationId);
	}

	/**
	* Returns the registration where groupId = &#63; and registrationId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param registrationId the registration ID
	* @return the matching registration, or <code>null</code> if a matching registration could not be found
	*/
	public static Registration fetchByG_REGID(long groupId, long registrationId) {
		return getPersistence().fetchByG_REGID(groupId, registrationId);
	}

	/**
	* Returns the registration where groupId = &#63; and registrationId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param registrationId the registration ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching registration, or <code>null</code> if a matching registration could not be found
	*/
	public static Registration fetchByG_REGID(long groupId,
		long registrationId, boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByG_REGID(groupId, registrationId, retrieveFromCache);
	}

	/**
	* Removes the registration where groupId = &#63; and registrationId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param registrationId the registration ID
	* @return the registration that was removed
	*/
	public static Registration removeByG_REGID(long groupId, long registrationId)
		throws org.opencps.dossiermgt.exception.NoSuchRegistrationException {
		return getPersistence().removeByG_REGID(groupId, registrationId);
	}

	/**
	* Returns the number of registrations where groupId = &#63; and registrationId = &#63;.
	*
	* @param groupId the group ID
	* @param registrationId the registration ID
	* @return the number of matching registrations
	*/
	public static int countByG_REGID(long groupId, long registrationId) {
		return getPersistence().countByG_REGID(groupId, registrationId);
	}

	/**
	* Returns all the registrations where groupId = &#63; and userId = &#63; and submitting = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param submitting the submitting
	* @return the matching registrations
	*/
	public static List<Registration> findByF_USERID_SUBMITTING(long groupId,
		long userId, boolean submitting) {
		return getPersistence()
				   .findByF_USERID_SUBMITTING(groupId, userId, submitting);
	}

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
	public static List<Registration> findByF_USERID_SUBMITTING(long groupId,
		long userId, boolean submitting, int start, int end) {
		return getPersistence()
				   .findByF_USERID_SUBMITTING(groupId, userId, submitting,
			start, end);
	}

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
	public static List<Registration> findByF_USERID_SUBMITTING(long groupId,
		long userId, boolean submitting, int start, int end,
		OrderByComparator<Registration> orderByComparator) {
		return getPersistence()
				   .findByF_USERID_SUBMITTING(groupId, userId, submitting,
			start, end, orderByComparator);
	}

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
	public static List<Registration> findByF_USERID_SUBMITTING(long groupId,
		long userId, boolean submitting, int start, int end,
		OrderByComparator<Registration> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByF_USERID_SUBMITTING(groupId, userId, submitting,
			start, end, orderByComparator, retrieveFromCache);
	}

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
	public static Registration findByF_USERID_SUBMITTING_First(long groupId,
		long userId, boolean submitting,
		OrderByComparator<Registration> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchRegistrationException {
		return getPersistence()
				   .findByF_USERID_SUBMITTING_First(groupId, userId,
			submitting, orderByComparator);
	}

	/**
	* Returns the first registration in the ordered set where groupId = &#63; and userId = &#63; and submitting = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param submitting the submitting
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching registration, or <code>null</code> if a matching registration could not be found
	*/
	public static Registration fetchByF_USERID_SUBMITTING_First(long groupId,
		long userId, boolean submitting,
		OrderByComparator<Registration> orderByComparator) {
		return getPersistence()
				   .fetchByF_USERID_SUBMITTING_First(groupId, userId,
			submitting, orderByComparator);
	}

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
	public static Registration findByF_USERID_SUBMITTING_Last(long groupId,
		long userId, boolean submitting,
		OrderByComparator<Registration> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchRegistrationException {
		return getPersistence()
				   .findByF_USERID_SUBMITTING_Last(groupId, userId, submitting,
			orderByComparator);
	}

	/**
	* Returns the last registration in the ordered set where groupId = &#63; and userId = &#63; and submitting = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param submitting the submitting
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching registration, or <code>null</code> if a matching registration could not be found
	*/
	public static Registration fetchByF_USERID_SUBMITTING_Last(long groupId,
		long userId, boolean submitting,
		OrderByComparator<Registration> orderByComparator) {
		return getPersistence()
				   .fetchByF_USERID_SUBMITTING_Last(groupId, userId,
			submitting, orderByComparator);
	}

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
	public static Registration[] findByF_USERID_SUBMITTING_PrevAndNext(
		long registrationId, long groupId, long userId, boolean submitting,
		OrderByComparator<Registration> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchRegistrationException {
		return getPersistence()
				   .findByF_USERID_SUBMITTING_PrevAndNext(registrationId,
			groupId, userId, submitting, orderByComparator);
	}

	/**
	* Removes all the registrations where groupId = &#63; and userId = &#63; and submitting = &#63; from the database.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param submitting the submitting
	*/
	public static void removeByF_USERID_SUBMITTING(long groupId, long userId,
		boolean submitting) {
		getPersistence().removeByF_USERID_SUBMITTING(groupId, userId, submitting);
	}

	/**
	* Returns the number of registrations where groupId = &#63; and userId = &#63; and submitting = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param submitting the submitting
	* @return the number of matching registrations
	*/
	public static int countByF_USERID_SUBMITTING(long groupId, long userId,
		boolean submitting) {
		return getPersistence()
				   .countByF_USERID_SUBMITTING(groupId, userId, submitting);
	}

	/**
	* Returns all the registrations where groupId = &#63; and applicantIdNo = &#63; and govAgencyCode = &#63; and registrationState = &#63;.
	*
	* @param groupId the group ID
	* @param applicantIdNo the applicant ID no
	* @param govAgencyCode the gov agency code
	* @param registrationState the registration state
	* @return the matching registrations
	*/
	public static List<Registration> findByG_APPNO_GOVCODE(long groupId,
		String applicantIdNo, String govAgencyCode, int registrationState) {
		return getPersistence()
				   .findByG_APPNO_GOVCODE(groupId, applicantIdNo,
			govAgencyCode, registrationState);
	}

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
	public static List<Registration> findByG_APPNO_GOVCODE(long groupId,
		String applicantIdNo, String govAgencyCode, int registrationState,
		int start, int end) {
		return getPersistence()
				   .findByG_APPNO_GOVCODE(groupId, applicantIdNo,
			govAgencyCode, registrationState, start, end);
	}

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
	public static List<Registration> findByG_APPNO_GOVCODE(long groupId,
		String applicantIdNo, String govAgencyCode, int registrationState,
		int start, int end, OrderByComparator<Registration> orderByComparator) {
		return getPersistence()
				   .findByG_APPNO_GOVCODE(groupId, applicantIdNo,
			govAgencyCode, registrationState, start, end, orderByComparator);
	}

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
	public static List<Registration> findByG_APPNO_GOVCODE(long groupId,
		String applicantIdNo, String govAgencyCode, int registrationState,
		int start, int end, OrderByComparator<Registration> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByG_APPNO_GOVCODE(groupId, applicantIdNo,
			govAgencyCode, registrationState, start, end, orderByComparator,
			retrieveFromCache);
	}

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
	public static Registration findByG_APPNO_GOVCODE_First(long groupId,
		String applicantIdNo, String govAgencyCode, int registrationState,
		OrderByComparator<Registration> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchRegistrationException {
		return getPersistence()
				   .findByG_APPNO_GOVCODE_First(groupId, applicantIdNo,
			govAgencyCode, registrationState, orderByComparator);
	}

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
	public static Registration fetchByG_APPNO_GOVCODE_First(long groupId,
		String applicantIdNo, String govAgencyCode, int registrationState,
		OrderByComparator<Registration> orderByComparator) {
		return getPersistence()
				   .fetchByG_APPNO_GOVCODE_First(groupId, applicantIdNo,
			govAgencyCode, registrationState, orderByComparator);
	}

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
	public static Registration findByG_APPNO_GOVCODE_Last(long groupId,
		String applicantIdNo, String govAgencyCode, int registrationState,
		OrderByComparator<Registration> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchRegistrationException {
		return getPersistence()
				   .findByG_APPNO_GOVCODE_Last(groupId, applicantIdNo,
			govAgencyCode, registrationState, orderByComparator);
	}

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
	public static Registration fetchByG_APPNO_GOVCODE_Last(long groupId,
		String applicantIdNo, String govAgencyCode, int registrationState,
		OrderByComparator<Registration> orderByComparator) {
		return getPersistence()
				   .fetchByG_APPNO_GOVCODE_Last(groupId, applicantIdNo,
			govAgencyCode, registrationState, orderByComparator);
	}

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
	public static Registration[] findByG_APPNO_GOVCODE_PrevAndNext(
		long registrationId, long groupId, String applicantIdNo,
		String govAgencyCode, int registrationState,
		OrderByComparator<Registration> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchRegistrationException {
		return getPersistence()
				   .findByG_APPNO_GOVCODE_PrevAndNext(registrationId, groupId,
			applicantIdNo, govAgencyCode, registrationState, orderByComparator);
	}

	/**
	* Removes all the registrations where groupId = &#63; and applicantIdNo = &#63; and govAgencyCode = &#63; and registrationState = &#63; from the database.
	*
	* @param groupId the group ID
	* @param applicantIdNo the applicant ID no
	* @param govAgencyCode the gov agency code
	* @param registrationState the registration state
	*/
	public static void removeByG_APPNO_GOVCODE(long groupId,
		String applicantIdNo, String govAgencyCode, int registrationState) {
		getPersistence()
			.removeByG_APPNO_GOVCODE(groupId, applicantIdNo, govAgencyCode,
			registrationState);
	}

	/**
	* Returns the number of registrations where groupId = &#63; and applicantIdNo = &#63; and govAgencyCode = &#63; and registrationState = &#63;.
	*
	* @param groupId the group ID
	* @param applicantIdNo the applicant ID no
	* @param govAgencyCode the gov agency code
	* @param registrationState the registration state
	* @return the number of matching registrations
	*/
	public static int countByG_APPNO_GOVCODE(long groupId,
		String applicantIdNo, String govAgencyCode, int registrationState) {
		return getPersistence()
				   .countByG_APPNO_GOVCODE(groupId, applicantIdNo,
			govAgencyCode, registrationState);
	}

	/**
	* Returns all the registrations where groupId = &#63; and userId = &#63; and registrationState = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param registrationState the registration state
	* @return the matching registrations
	*/
	public static List<Registration> findByG_USER_STATE(long groupId,
		long userId, int registrationState) {
		return getPersistence()
				   .findByG_USER_STATE(groupId, userId, registrationState);
	}

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
	public static List<Registration> findByG_USER_STATE(long groupId,
		long userId, int registrationState, int start, int end) {
		return getPersistence()
				   .findByG_USER_STATE(groupId, userId, registrationState,
			start, end);
	}

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
	public static List<Registration> findByG_USER_STATE(long groupId,
		long userId, int registrationState, int start, int end,
		OrderByComparator<Registration> orderByComparator) {
		return getPersistence()
				   .findByG_USER_STATE(groupId, userId, registrationState,
			start, end, orderByComparator);
	}

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
	public static List<Registration> findByG_USER_STATE(long groupId,
		long userId, int registrationState, int start, int end,
		OrderByComparator<Registration> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByG_USER_STATE(groupId, userId, registrationState,
			start, end, orderByComparator, retrieveFromCache);
	}

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
	public static Registration findByG_USER_STATE_First(long groupId,
		long userId, int registrationState,
		OrderByComparator<Registration> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchRegistrationException {
		return getPersistence()
				   .findByG_USER_STATE_First(groupId, userId,
			registrationState, orderByComparator);
	}

	/**
	* Returns the first registration in the ordered set where groupId = &#63; and userId = &#63; and registrationState = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param registrationState the registration state
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching registration, or <code>null</code> if a matching registration could not be found
	*/
	public static Registration fetchByG_USER_STATE_First(long groupId,
		long userId, int registrationState,
		OrderByComparator<Registration> orderByComparator) {
		return getPersistence()
				   .fetchByG_USER_STATE_First(groupId, userId,
			registrationState, orderByComparator);
	}

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
	public static Registration findByG_USER_STATE_Last(long groupId,
		long userId, int registrationState,
		OrderByComparator<Registration> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchRegistrationException {
		return getPersistence()
				   .findByG_USER_STATE_Last(groupId, userId, registrationState,
			orderByComparator);
	}

	/**
	* Returns the last registration in the ordered set where groupId = &#63; and userId = &#63; and registrationState = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param registrationState the registration state
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching registration, or <code>null</code> if a matching registration could not be found
	*/
	public static Registration fetchByG_USER_STATE_Last(long groupId,
		long userId, int registrationState,
		OrderByComparator<Registration> orderByComparator) {
		return getPersistence()
				   .fetchByG_USER_STATE_Last(groupId, userId,
			registrationState, orderByComparator);
	}

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
	public static Registration[] findByG_USER_STATE_PrevAndNext(
		long registrationId, long groupId, long userId, int registrationState,
		OrderByComparator<Registration> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchRegistrationException {
		return getPersistence()
				   .findByG_USER_STATE_PrevAndNext(registrationId, groupId,
			userId, registrationState, orderByComparator);
	}

	/**
	* Removes all the registrations where groupId = &#63; and userId = &#63; and registrationState = &#63; from the database.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param registrationState the registration state
	*/
	public static void removeByG_USER_STATE(long groupId, long userId,
		int registrationState) {
		getPersistence().removeByG_USER_STATE(groupId, userId, registrationState);
	}

	/**
	* Returns the number of registrations where groupId = &#63; and userId = &#63; and registrationState = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param registrationState the registration state
	* @return the number of matching registrations
	*/
	public static int countByG_USER_STATE(long groupId, long userId,
		int registrationState) {
		return getPersistence()
				   .countByG_USER_STATE(groupId, userId, registrationState);
	}

	/**
	* Returns the registration where applicantIdNo = &#63; or throws a {@link NoSuchRegistrationException} if it could not be found.
	*
	* @param applicantIdNo the applicant ID no
	* @return the matching registration
	* @throws NoSuchRegistrationException if a matching registration could not be found
	*/
	public static Registration findByREG_APPNO(String applicantIdNo)
		throws org.opencps.dossiermgt.exception.NoSuchRegistrationException {
		return getPersistence().findByREG_APPNO(applicantIdNo);
	}

	/**
	* Returns the registration where applicantIdNo = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param applicantIdNo the applicant ID no
	* @return the matching registration, or <code>null</code> if a matching registration could not be found
	*/
	public static Registration fetchByREG_APPNO(String applicantIdNo) {
		return getPersistence().fetchByREG_APPNO(applicantIdNo);
	}

	/**
	* Returns the registration where applicantIdNo = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param applicantIdNo the applicant ID no
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching registration, or <code>null</code> if a matching registration could not be found
	*/
	public static Registration fetchByREG_APPNO(String applicantIdNo,
		boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByREG_APPNO(applicantIdNo, retrieveFromCache);
	}

	/**
	* Removes the registration where applicantIdNo = &#63; from the database.
	*
	* @param applicantIdNo the applicant ID no
	* @return the registration that was removed
	*/
	public static Registration removeByREG_APPNO(String applicantIdNo)
		throws org.opencps.dossiermgt.exception.NoSuchRegistrationException {
		return getPersistence().removeByREG_APPNO(applicantIdNo);
	}

	/**
	* Returns the number of registrations where applicantIdNo = &#63;.
	*
	* @param applicantIdNo the applicant ID no
	* @return the number of matching registrations
	*/
	public static int countByREG_APPNO(String applicantIdNo) {
		return getPersistence().countByREG_APPNO(applicantIdNo);
	}

	/**
	* Caches the registration in the entity cache if it is enabled.
	*
	* @param registration the registration
	*/
	public static void cacheResult(Registration registration) {
		getPersistence().cacheResult(registration);
	}

	/**
	* Caches the registrations in the entity cache if it is enabled.
	*
	* @param registrations the registrations
	*/
	public static void cacheResult(List<Registration> registrations) {
		getPersistence().cacheResult(registrations);
	}

	/**
	* Creates a new registration with the primary key. Does not add the registration to the database.
	*
	* @param registrationId the primary key for the new registration
	* @return the new registration
	*/
	public static Registration create(long registrationId) {
		return getPersistence().create(registrationId);
	}

	/**
	* Removes the registration with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param registrationId the primary key of the registration
	* @return the registration that was removed
	* @throws NoSuchRegistrationException if a registration with the primary key could not be found
	*/
	public static Registration remove(long registrationId)
		throws org.opencps.dossiermgt.exception.NoSuchRegistrationException {
		return getPersistence().remove(registrationId);
	}

	public static Registration updateImpl(Registration registration) {
		return getPersistence().updateImpl(registration);
	}

	/**
	* Returns the registration with the primary key or throws a {@link NoSuchRegistrationException} if it could not be found.
	*
	* @param registrationId the primary key of the registration
	* @return the registration
	* @throws NoSuchRegistrationException if a registration with the primary key could not be found
	*/
	public static Registration findByPrimaryKey(long registrationId)
		throws org.opencps.dossiermgt.exception.NoSuchRegistrationException {
		return getPersistence().findByPrimaryKey(registrationId);
	}

	/**
	* Returns the registration with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param registrationId the primary key of the registration
	* @return the registration, or <code>null</code> if a registration with the primary key could not be found
	*/
	public static Registration fetchByPrimaryKey(long registrationId) {
		return getPersistence().fetchByPrimaryKey(registrationId);
	}

	public static java.util.Map<java.io.Serializable, Registration> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the registrations.
	*
	* @return the registrations
	*/
	public static List<Registration> findAll() {
		return getPersistence().findAll();
	}

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
	public static List<Registration> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

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
	public static List<Registration> findAll(int start, int end,
		OrderByComparator<Registration> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

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
	public static List<Registration> findAll(int start, int end,
		OrderByComparator<Registration> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the registrations from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of registrations.
	*
	* @return the number of registrations
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static RegistrationPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<RegistrationPersistence, RegistrationPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(RegistrationPersistence.class);

		ServiceTracker<RegistrationPersistence, RegistrationPersistence> serviceTracker =
			new ServiceTracker<RegistrationPersistence, RegistrationPersistence>(bundle.getBundleContext(),
				RegistrationPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}