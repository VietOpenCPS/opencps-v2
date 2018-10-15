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

import org.opencps.dossiermgt.model.RegistrationForm;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the registration form service. This utility wraps {@link org.opencps.dossiermgt.service.persistence.impl.RegistrationFormPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author huymq
 * @see RegistrationFormPersistence
 * @see org.opencps.dossiermgt.service.persistence.impl.RegistrationFormPersistenceImpl
 * @generated
 */
@ProviderType
public class RegistrationFormUtil {
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
	public static void clearCache(RegistrationForm registrationForm) {
		getPersistence().clearCache(registrationForm);
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
	public static List<RegistrationForm> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<RegistrationForm> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<RegistrationForm> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<RegistrationForm> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static RegistrationForm update(RegistrationForm registrationForm) {
		return getPersistence().update(registrationForm);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static RegistrationForm update(RegistrationForm registrationForm,
		ServiceContext serviceContext) {
		return getPersistence().update(registrationForm, serviceContext);
	}

	/**
	* Returns all the registration forms where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching registration forms
	*/
	public static List<RegistrationForm> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

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
	public static List<RegistrationForm> findByUuid(String uuid, int start,
		int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

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
	public static List<RegistrationForm> findByUuid(String uuid, int start,
		int end, OrderByComparator<RegistrationForm> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

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
	public static List<RegistrationForm> findByUuid(String uuid, int start,
		int end, OrderByComparator<RegistrationForm> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first registration form in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching registration form
	* @throws NoSuchRegistrationFormException if a matching registration form could not be found
	*/
	public static RegistrationForm findByUuid_First(String uuid,
		OrderByComparator<RegistrationForm> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchRegistrationFormException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first registration form in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching registration form, or <code>null</code> if a matching registration form could not be found
	*/
	public static RegistrationForm fetchByUuid_First(String uuid,
		OrderByComparator<RegistrationForm> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last registration form in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching registration form
	* @throws NoSuchRegistrationFormException if a matching registration form could not be found
	*/
	public static RegistrationForm findByUuid_Last(String uuid,
		OrderByComparator<RegistrationForm> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchRegistrationFormException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last registration form in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching registration form, or <code>null</code> if a matching registration form could not be found
	*/
	public static RegistrationForm fetchByUuid_Last(String uuid,
		OrderByComparator<RegistrationForm> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the registration forms before and after the current registration form in the ordered set where uuid = &#63;.
	*
	* @param registrationFormId the primary key of the current registration form
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next registration form
	* @throws NoSuchRegistrationFormException if a registration form with the primary key could not be found
	*/
	public static RegistrationForm[] findByUuid_PrevAndNext(
		long registrationFormId, String uuid,
		OrderByComparator<RegistrationForm> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchRegistrationFormException {
		return getPersistence()
				   .findByUuid_PrevAndNext(registrationFormId, uuid,
			orderByComparator);
	}

	/**
	* Removes all the registration forms where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of registration forms where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching registration forms
	*/
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the registration form where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchRegistrationFormException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching registration form
	* @throws NoSuchRegistrationFormException if a matching registration form could not be found
	*/
	public static RegistrationForm findByUUID_G(String uuid, long groupId)
		throws org.opencps.dossiermgt.exception.NoSuchRegistrationFormException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the registration form where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching registration form, or <code>null</code> if a matching registration form could not be found
	*/
	public static RegistrationForm fetchByUUID_G(String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the registration form where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching registration form, or <code>null</code> if a matching registration form could not be found
	*/
	public static RegistrationForm fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the registration form where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the registration form that was removed
	*/
	public static RegistrationForm removeByUUID_G(String uuid, long groupId)
		throws org.opencps.dossiermgt.exception.NoSuchRegistrationFormException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of registration forms where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching registration forms
	*/
	public static int countByUUID_G(String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the registration forms where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching registration forms
	*/
	public static List<RegistrationForm> findByUuid_C(String uuid,
		long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

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
	public static List<RegistrationForm> findByUuid_C(String uuid,
		long companyId, int start, int end) {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

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
	public static List<RegistrationForm> findByUuid_C(String uuid,
		long companyId, int start, int end,
		OrderByComparator<RegistrationForm> orderByComparator) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

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
	public static List<RegistrationForm> findByUuid_C(String uuid,
		long companyId, int start, int end,
		OrderByComparator<RegistrationForm> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first registration form in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching registration form
	* @throws NoSuchRegistrationFormException if a matching registration form could not be found
	*/
	public static RegistrationForm findByUuid_C_First(String uuid,
		long companyId, OrderByComparator<RegistrationForm> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchRegistrationFormException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first registration form in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching registration form, or <code>null</code> if a matching registration form could not be found
	*/
	public static RegistrationForm fetchByUuid_C_First(String uuid,
		long companyId, OrderByComparator<RegistrationForm> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last registration form in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching registration form
	* @throws NoSuchRegistrationFormException if a matching registration form could not be found
	*/
	public static RegistrationForm findByUuid_C_Last(String uuid,
		long companyId, OrderByComparator<RegistrationForm> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchRegistrationFormException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last registration form in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching registration form, or <code>null</code> if a matching registration form could not be found
	*/
	public static RegistrationForm fetchByUuid_C_Last(String uuid,
		long companyId, OrderByComparator<RegistrationForm> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

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
	public static RegistrationForm[] findByUuid_C_PrevAndNext(
		long registrationFormId, String uuid, long companyId,
		OrderByComparator<RegistrationForm> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchRegistrationFormException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(registrationFormId, uuid,
			companyId, orderByComparator);
	}

	/**
	* Removes all the registration forms where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public static void removeByUuid_C(String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of registration forms where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching registration forms
	*/
	public static int countByUuid_C(String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Returns all the registration forms where groupId = &#63; and registrationId = &#63;.
	*
	* @param groupId the group ID
	* @param registrationId the registration ID
	* @return the matching registration forms
	*/
	public static List<RegistrationForm> findByG_REGID(long groupId,
		long registrationId) {
		return getPersistence().findByG_REGID(groupId, registrationId);
	}

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
	public static List<RegistrationForm> findByG_REGID(long groupId,
		long registrationId, int start, int end) {
		return getPersistence()
				   .findByG_REGID(groupId, registrationId, start, end);
	}

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
	public static List<RegistrationForm> findByG_REGID(long groupId,
		long registrationId, int start, int end,
		OrderByComparator<RegistrationForm> orderByComparator) {
		return getPersistence()
				   .findByG_REGID(groupId, registrationId, start, end,
			orderByComparator);
	}

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
	public static List<RegistrationForm> findByG_REGID(long groupId,
		long registrationId, int start, int end,
		OrderByComparator<RegistrationForm> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByG_REGID(groupId, registrationId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first registration form in the ordered set where groupId = &#63; and registrationId = &#63;.
	*
	* @param groupId the group ID
	* @param registrationId the registration ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching registration form
	* @throws NoSuchRegistrationFormException if a matching registration form could not be found
	*/
	public static RegistrationForm findByG_REGID_First(long groupId,
		long registrationId,
		OrderByComparator<RegistrationForm> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchRegistrationFormException {
		return getPersistence()
				   .findByG_REGID_First(groupId, registrationId,
			orderByComparator);
	}

	/**
	* Returns the first registration form in the ordered set where groupId = &#63; and registrationId = &#63;.
	*
	* @param groupId the group ID
	* @param registrationId the registration ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching registration form, or <code>null</code> if a matching registration form could not be found
	*/
	public static RegistrationForm fetchByG_REGID_First(long groupId,
		long registrationId,
		OrderByComparator<RegistrationForm> orderByComparator) {
		return getPersistence()
				   .fetchByG_REGID_First(groupId, registrationId,
			orderByComparator);
	}

	/**
	* Returns the last registration form in the ordered set where groupId = &#63; and registrationId = &#63;.
	*
	* @param groupId the group ID
	* @param registrationId the registration ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching registration form
	* @throws NoSuchRegistrationFormException if a matching registration form could not be found
	*/
	public static RegistrationForm findByG_REGID_Last(long groupId,
		long registrationId,
		OrderByComparator<RegistrationForm> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchRegistrationFormException {
		return getPersistence()
				   .findByG_REGID_Last(groupId, registrationId,
			orderByComparator);
	}

	/**
	* Returns the last registration form in the ordered set where groupId = &#63; and registrationId = &#63;.
	*
	* @param groupId the group ID
	* @param registrationId the registration ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching registration form, or <code>null</code> if a matching registration form could not be found
	*/
	public static RegistrationForm fetchByG_REGID_Last(long groupId,
		long registrationId,
		OrderByComparator<RegistrationForm> orderByComparator) {
		return getPersistence()
				   .fetchByG_REGID_Last(groupId, registrationId,
			orderByComparator);
	}

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
	public static RegistrationForm[] findByG_REGID_PrevAndNext(
		long registrationFormId, long groupId, long registrationId,
		OrderByComparator<RegistrationForm> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchRegistrationFormException {
		return getPersistence()
				   .findByG_REGID_PrevAndNext(registrationFormId, groupId,
			registrationId, orderByComparator);
	}

	/**
	* Removes all the registration forms where groupId = &#63; and registrationId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param registrationId the registration ID
	*/
	public static void removeByG_REGID(long groupId, long registrationId) {
		getPersistence().removeByG_REGID(groupId, registrationId);
	}

	/**
	* Returns the number of registration forms where groupId = &#63; and registrationId = &#63;.
	*
	* @param groupId the group ID
	* @param registrationId the registration ID
	* @return the number of matching registration forms
	*/
	public static int countByG_REGID(long groupId, long registrationId) {
		return getPersistence().countByG_REGID(groupId, registrationId);
	}

	/**
	* Returns the registration form where groupId = &#63; and registrationId = &#63; and referenceUid = &#63; or throws a {@link NoSuchRegistrationFormException} if it could not be found.
	*
	* @param groupId the group ID
	* @param registrationId the registration ID
	* @param referenceUid the reference uid
	* @return the matching registration form
	* @throws NoSuchRegistrationFormException if a matching registration form could not be found
	*/
	public static RegistrationForm findByG_REGID_REFID(long groupId,
		long registrationId, String referenceUid)
		throws org.opencps.dossiermgt.exception.NoSuchRegistrationFormException {
		return getPersistence()
				   .findByG_REGID_REFID(groupId, registrationId, referenceUid);
	}

	/**
	* Returns the registration form where groupId = &#63; and registrationId = &#63; and referenceUid = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param registrationId the registration ID
	* @param referenceUid the reference uid
	* @return the matching registration form, or <code>null</code> if a matching registration form could not be found
	*/
	public static RegistrationForm fetchByG_REGID_REFID(long groupId,
		long registrationId, String referenceUid) {
		return getPersistence()
				   .fetchByG_REGID_REFID(groupId, registrationId, referenceUid);
	}

	/**
	* Returns the registration form where groupId = &#63; and registrationId = &#63; and referenceUid = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param registrationId the registration ID
	* @param referenceUid the reference uid
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching registration form, or <code>null</code> if a matching registration form could not be found
	*/
	public static RegistrationForm fetchByG_REGID_REFID(long groupId,
		long registrationId, String referenceUid, boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByG_REGID_REFID(groupId, registrationId, referenceUid,
			retrieveFromCache);
	}

	/**
	* Removes the registration form where groupId = &#63; and registrationId = &#63; and referenceUid = &#63; from the database.
	*
	* @param groupId the group ID
	* @param registrationId the registration ID
	* @param referenceUid the reference uid
	* @return the registration form that was removed
	*/
	public static RegistrationForm removeByG_REGID_REFID(long groupId,
		long registrationId, String referenceUid)
		throws org.opencps.dossiermgt.exception.NoSuchRegistrationFormException {
		return getPersistence()
				   .removeByG_REGID_REFID(groupId, registrationId, referenceUid);
	}

	/**
	* Returns the number of registration forms where groupId = &#63; and registrationId = &#63; and referenceUid = &#63;.
	*
	* @param groupId the group ID
	* @param registrationId the registration ID
	* @param referenceUid the reference uid
	* @return the number of matching registration forms
	*/
	public static int countByG_REGID_REFID(long groupId, long registrationId,
		String referenceUid) {
		return getPersistence()
				   .countByG_REGID_REFID(groupId, registrationId, referenceUid);
	}

	/**
	* Returns all the registration forms where registrationId = &#63; and isNew = &#63;.
	*
	* @param registrationId the registration ID
	* @param isNew the is new
	* @return the matching registration forms
	*/
	public static List<RegistrationForm> findByG_REGID_ISNEW(
		long registrationId, boolean isNew) {
		return getPersistence().findByG_REGID_ISNEW(registrationId, isNew);
	}

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
	public static List<RegistrationForm> findByG_REGID_ISNEW(
		long registrationId, boolean isNew, int start, int end) {
		return getPersistence()
				   .findByG_REGID_ISNEW(registrationId, isNew, start, end);
	}

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
	public static List<RegistrationForm> findByG_REGID_ISNEW(
		long registrationId, boolean isNew, int start, int end,
		OrderByComparator<RegistrationForm> orderByComparator) {
		return getPersistence()
				   .findByG_REGID_ISNEW(registrationId, isNew, start, end,
			orderByComparator);
	}

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
	public static List<RegistrationForm> findByG_REGID_ISNEW(
		long registrationId, boolean isNew, int start, int end,
		OrderByComparator<RegistrationForm> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByG_REGID_ISNEW(registrationId, isNew, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first registration form in the ordered set where registrationId = &#63; and isNew = &#63;.
	*
	* @param registrationId the registration ID
	* @param isNew the is new
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching registration form
	* @throws NoSuchRegistrationFormException if a matching registration form could not be found
	*/
	public static RegistrationForm findByG_REGID_ISNEW_First(
		long registrationId, boolean isNew,
		OrderByComparator<RegistrationForm> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchRegistrationFormException {
		return getPersistence()
				   .findByG_REGID_ISNEW_First(registrationId, isNew,
			orderByComparator);
	}

	/**
	* Returns the first registration form in the ordered set where registrationId = &#63; and isNew = &#63;.
	*
	* @param registrationId the registration ID
	* @param isNew the is new
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching registration form, or <code>null</code> if a matching registration form could not be found
	*/
	public static RegistrationForm fetchByG_REGID_ISNEW_First(
		long registrationId, boolean isNew,
		OrderByComparator<RegistrationForm> orderByComparator) {
		return getPersistence()
				   .fetchByG_REGID_ISNEW_First(registrationId, isNew,
			orderByComparator);
	}

	/**
	* Returns the last registration form in the ordered set where registrationId = &#63; and isNew = &#63;.
	*
	* @param registrationId the registration ID
	* @param isNew the is new
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching registration form
	* @throws NoSuchRegistrationFormException if a matching registration form could not be found
	*/
	public static RegistrationForm findByG_REGID_ISNEW_Last(
		long registrationId, boolean isNew,
		OrderByComparator<RegistrationForm> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchRegistrationFormException {
		return getPersistence()
				   .findByG_REGID_ISNEW_Last(registrationId, isNew,
			orderByComparator);
	}

	/**
	* Returns the last registration form in the ordered set where registrationId = &#63; and isNew = &#63;.
	*
	* @param registrationId the registration ID
	* @param isNew the is new
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching registration form, or <code>null</code> if a matching registration form could not be found
	*/
	public static RegistrationForm fetchByG_REGID_ISNEW_Last(
		long registrationId, boolean isNew,
		OrderByComparator<RegistrationForm> orderByComparator) {
		return getPersistence()
				   .fetchByG_REGID_ISNEW_Last(registrationId, isNew,
			orderByComparator);
	}

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
	public static RegistrationForm[] findByG_REGID_ISNEW_PrevAndNext(
		long registrationFormId, long registrationId, boolean isNew,
		OrderByComparator<RegistrationForm> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchRegistrationFormException {
		return getPersistence()
				   .findByG_REGID_ISNEW_PrevAndNext(registrationFormId,
			registrationId, isNew, orderByComparator);
	}

	/**
	* Removes all the registration forms where registrationId = &#63; and isNew = &#63; from the database.
	*
	* @param registrationId the registration ID
	* @param isNew the is new
	*/
	public static void removeByG_REGID_ISNEW(long registrationId, boolean isNew) {
		getPersistence().removeByG_REGID_ISNEW(registrationId, isNew);
	}

	/**
	* Returns the number of registration forms where registrationId = &#63; and isNew = &#63;.
	*
	* @param registrationId the registration ID
	* @param isNew the is new
	* @return the number of matching registration forms
	*/
	public static int countByG_REGID_ISNEW(long registrationId, boolean isNew) {
		return getPersistence().countByG_REGID_ISNEW(registrationId, isNew);
	}

	/**
	* Returns the registration form where registrationId = &#63; and formNo = &#63; or throws a {@link NoSuchRegistrationFormException} if it could not be found.
	*
	* @param registrationId the registration ID
	* @param formNo the form no
	* @return the matching registration form
	* @throws NoSuchRegistrationFormException if a matching registration form could not be found
	*/
	public static RegistrationForm findByREGID_FORMNO(long registrationId,
		String formNo)
		throws org.opencps.dossiermgt.exception.NoSuchRegistrationFormException {
		return getPersistence().findByREGID_FORMNO(registrationId, formNo);
	}

	/**
	* Returns the registration form where registrationId = &#63; and formNo = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param registrationId the registration ID
	* @param formNo the form no
	* @return the matching registration form, or <code>null</code> if a matching registration form could not be found
	*/
	public static RegistrationForm fetchByREGID_FORMNO(long registrationId,
		String formNo) {
		return getPersistence().fetchByREGID_FORMNO(registrationId, formNo);
	}

	/**
	* Returns the registration form where registrationId = &#63; and formNo = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param registrationId the registration ID
	* @param formNo the form no
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching registration form, or <code>null</code> if a matching registration form could not be found
	*/
	public static RegistrationForm fetchByREGID_FORMNO(long registrationId,
		String formNo, boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByREGID_FORMNO(registrationId, formNo,
			retrieveFromCache);
	}

	/**
	* Removes the registration form where registrationId = &#63; and formNo = &#63; from the database.
	*
	* @param registrationId the registration ID
	* @param formNo the form no
	* @return the registration form that was removed
	*/
	public static RegistrationForm removeByREGID_FORMNO(long registrationId,
		String formNo)
		throws org.opencps.dossiermgt.exception.NoSuchRegistrationFormException {
		return getPersistence().removeByREGID_FORMNO(registrationId, formNo);
	}

	/**
	* Returns the number of registration forms where registrationId = &#63; and formNo = &#63;.
	*
	* @param registrationId the registration ID
	* @param formNo the form no
	* @return the number of matching registration forms
	*/
	public static int countByREGID_FORMNO(long registrationId, String formNo) {
		return getPersistence().countByREGID_FORMNO(registrationId, formNo);
	}

	/**
	* Returns all the registration forms where groupId = &#63; and registrationId = &#63; and formNo = &#63;.
	*
	* @param groupId the group ID
	* @param registrationId the registration ID
	* @param formNo the form no
	* @return the matching registration forms
	*/
	public static List<RegistrationForm> findByG_REGID_FORMNO(long groupId,
		long registrationId, String formNo) {
		return getPersistence()
				   .findByG_REGID_FORMNO(groupId, registrationId, formNo);
	}

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
	public static List<RegistrationForm> findByG_REGID_FORMNO(long groupId,
		long registrationId, String formNo, int start, int end) {
		return getPersistence()
				   .findByG_REGID_FORMNO(groupId, registrationId, formNo,
			start, end);
	}

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
	public static List<RegistrationForm> findByG_REGID_FORMNO(long groupId,
		long registrationId, String formNo, int start, int end,
		OrderByComparator<RegistrationForm> orderByComparator) {
		return getPersistence()
				   .findByG_REGID_FORMNO(groupId, registrationId, formNo,
			start, end, orderByComparator);
	}

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
	public static List<RegistrationForm> findByG_REGID_FORMNO(long groupId,
		long registrationId, String formNo, int start, int end,
		OrderByComparator<RegistrationForm> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByG_REGID_FORMNO(groupId, registrationId, formNo,
			start, end, orderByComparator, retrieveFromCache);
	}

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
	public static RegistrationForm findByG_REGID_FORMNO_First(long groupId,
		long registrationId, String formNo,
		OrderByComparator<RegistrationForm> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchRegistrationFormException {
		return getPersistence()
				   .findByG_REGID_FORMNO_First(groupId, registrationId, formNo,
			orderByComparator);
	}

	/**
	* Returns the first registration form in the ordered set where groupId = &#63; and registrationId = &#63; and formNo = &#63;.
	*
	* @param groupId the group ID
	* @param registrationId the registration ID
	* @param formNo the form no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching registration form, or <code>null</code> if a matching registration form could not be found
	*/
	public static RegistrationForm fetchByG_REGID_FORMNO_First(long groupId,
		long registrationId, String formNo,
		OrderByComparator<RegistrationForm> orderByComparator) {
		return getPersistence()
				   .fetchByG_REGID_FORMNO_First(groupId, registrationId,
			formNo, orderByComparator);
	}

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
	public static RegistrationForm findByG_REGID_FORMNO_Last(long groupId,
		long registrationId, String formNo,
		OrderByComparator<RegistrationForm> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchRegistrationFormException {
		return getPersistence()
				   .findByG_REGID_FORMNO_Last(groupId, registrationId, formNo,
			orderByComparator);
	}

	/**
	* Returns the last registration form in the ordered set where groupId = &#63; and registrationId = &#63; and formNo = &#63;.
	*
	* @param groupId the group ID
	* @param registrationId the registration ID
	* @param formNo the form no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching registration form, or <code>null</code> if a matching registration form could not be found
	*/
	public static RegistrationForm fetchByG_REGID_FORMNO_Last(long groupId,
		long registrationId, String formNo,
		OrderByComparator<RegistrationForm> orderByComparator) {
		return getPersistence()
				   .fetchByG_REGID_FORMNO_Last(groupId, registrationId, formNo,
			orderByComparator);
	}

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
	public static RegistrationForm[] findByG_REGID_FORMNO_PrevAndNext(
		long registrationFormId, long groupId, long registrationId,
		String formNo, OrderByComparator<RegistrationForm> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchRegistrationFormException {
		return getPersistence()
				   .findByG_REGID_FORMNO_PrevAndNext(registrationFormId,
			groupId, registrationId, formNo, orderByComparator);
	}

	/**
	* Removes all the registration forms where groupId = &#63; and registrationId = &#63; and formNo = &#63; from the database.
	*
	* @param groupId the group ID
	* @param registrationId the registration ID
	* @param formNo the form no
	*/
	public static void removeByG_REGID_FORMNO(long groupId,
		long registrationId, String formNo) {
		getPersistence().removeByG_REGID_FORMNO(groupId, registrationId, formNo);
	}

	/**
	* Returns the number of registration forms where groupId = &#63; and registrationId = &#63; and formNo = &#63;.
	*
	* @param groupId the group ID
	* @param registrationId the registration ID
	* @param formNo the form no
	* @return the number of matching registration forms
	*/
	public static int countByG_REGID_FORMNO(long groupId, long registrationId,
		String formNo) {
		return getPersistence()
				   .countByG_REGID_FORMNO(groupId, registrationId, formNo);
	}

	/**
	* Returns all the registration forms where referenceUid = &#63;.
	*
	* @param referenceUid the reference uid
	* @return the matching registration forms
	*/
	public static List<RegistrationForm> findByF_REFID(String referenceUid) {
		return getPersistence().findByF_REFID(referenceUid);
	}

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
	public static List<RegistrationForm> findByF_REFID(String referenceUid,
		int start, int end) {
		return getPersistence().findByF_REFID(referenceUid, start, end);
	}

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
	public static List<RegistrationForm> findByF_REFID(String referenceUid,
		int start, int end,
		OrderByComparator<RegistrationForm> orderByComparator) {
		return getPersistence()
				   .findByF_REFID(referenceUid, start, end, orderByComparator);
	}

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
	public static List<RegistrationForm> findByF_REFID(String referenceUid,
		int start, int end,
		OrderByComparator<RegistrationForm> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByF_REFID(referenceUid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first registration form in the ordered set where referenceUid = &#63;.
	*
	* @param referenceUid the reference uid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching registration form
	* @throws NoSuchRegistrationFormException if a matching registration form could not be found
	*/
	public static RegistrationForm findByF_REFID_First(String referenceUid,
		OrderByComparator<RegistrationForm> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchRegistrationFormException {
		return getPersistence()
				   .findByF_REFID_First(referenceUid, orderByComparator);
	}

	/**
	* Returns the first registration form in the ordered set where referenceUid = &#63;.
	*
	* @param referenceUid the reference uid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching registration form, or <code>null</code> if a matching registration form could not be found
	*/
	public static RegistrationForm fetchByF_REFID_First(String referenceUid,
		OrderByComparator<RegistrationForm> orderByComparator) {
		return getPersistence()
				   .fetchByF_REFID_First(referenceUid, orderByComparator);
	}

	/**
	* Returns the last registration form in the ordered set where referenceUid = &#63;.
	*
	* @param referenceUid the reference uid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching registration form
	* @throws NoSuchRegistrationFormException if a matching registration form could not be found
	*/
	public static RegistrationForm findByF_REFID_Last(String referenceUid,
		OrderByComparator<RegistrationForm> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchRegistrationFormException {
		return getPersistence()
				   .findByF_REFID_Last(referenceUid, orderByComparator);
	}

	/**
	* Returns the last registration form in the ordered set where referenceUid = &#63;.
	*
	* @param referenceUid the reference uid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching registration form, or <code>null</code> if a matching registration form could not be found
	*/
	public static RegistrationForm fetchByF_REFID_Last(String referenceUid,
		OrderByComparator<RegistrationForm> orderByComparator) {
		return getPersistence()
				   .fetchByF_REFID_Last(referenceUid, orderByComparator);
	}

	/**
	* Returns the registration forms before and after the current registration form in the ordered set where referenceUid = &#63;.
	*
	* @param registrationFormId the primary key of the current registration form
	* @param referenceUid the reference uid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next registration form
	* @throws NoSuchRegistrationFormException if a registration form with the primary key could not be found
	*/
	public static RegistrationForm[] findByF_REFID_PrevAndNext(
		long registrationFormId, String referenceUid,
		OrderByComparator<RegistrationForm> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchRegistrationFormException {
		return getPersistence()
				   .findByF_REFID_PrevAndNext(registrationFormId, referenceUid,
			orderByComparator);
	}

	/**
	* Removes all the registration forms where referenceUid = &#63; from the database.
	*
	* @param referenceUid the reference uid
	*/
	public static void removeByF_REFID(String referenceUid) {
		getPersistence().removeByF_REFID(referenceUid);
	}

	/**
	* Returns the number of registration forms where referenceUid = &#63;.
	*
	* @param referenceUid the reference uid
	* @return the number of matching registration forms
	*/
	public static int countByF_REFID(String referenceUid) {
		return getPersistence().countByF_REFID(referenceUid);
	}

	/**
	* Caches the registration form in the entity cache if it is enabled.
	*
	* @param registrationForm the registration form
	*/
	public static void cacheResult(RegistrationForm registrationForm) {
		getPersistence().cacheResult(registrationForm);
	}

	/**
	* Caches the registration forms in the entity cache if it is enabled.
	*
	* @param registrationForms the registration forms
	*/
	public static void cacheResult(List<RegistrationForm> registrationForms) {
		getPersistence().cacheResult(registrationForms);
	}

	/**
	* Creates a new registration form with the primary key. Does not add the registration form to the database.
	*
	* @param registrationFormId the primary key for the new registration form
	* @return the new registration form
	*/
	public static RegistrationForm create(long registrationFormId) {
		return getPersistence().create(registrationFormId);
	}

	/**
	* Removes the registration form with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param registrationFormId the primary key of the registration form
	* @return the registration form that was removed
	* @throws NoSuchRegistrationFormException if a registration form with the primary key could not be found
	*/
	public static RegistrationForm remove(long registrationFormId)
		throws org.opencps.dossiermgt.exception.NoSuchRegistrationFormException {
		return getPersistence().remove(registrationFormId);
	}

	public static RegistrationForm updateImpl(RegistrationForm registrationForm) {
		return getPersistence().updateImpl(registrationForm);
	}

	/**
	* Returns the registration form with the primary key or throws a {@link NoSuchRegistrationFormException} if it could not be found.
	*
	* @param registrationFormId the primary key of the registration form
	* @return the registration form
	* @throws NoSuchRegistrationFormException if a registration form with the primary key could not be found
	*/
	public static RegistrationForm findByPrimaryKey(long registrationFormId)
		throws org.opencps.dossiermgt.exception.NoSuchRegistrationFormException {
		return getPersistence().findByPrimaryKey(registrationFormId);
	}

	/**
	* Returns the registration form with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param registrationFormId the primary key of the registration form
	* @return the registration form, or <code>null</code> if a registration form with the primary key could not be found
	*/
	public static RegistrationForm fetchByPrimaryKey(long registrationFormId) {
		return getPersistence().fetchByPrimaryKey(registrationFormId);
	}

	public static java.util.Map<java.io.Serializable, RegistrationForm> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the registration forms.
	*
	* @return the registration forms
	*/
	public static List<RegistrationForm> findAll() {
		return getPersistence().findAll();
	}

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
	public static List<RegistrationForm> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

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
	public static List<RegistrationForm> findAll(int start, int end,
		OrderByComparator<RegistrationForm> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

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
	public static List<RegistrationForm> findAll(int start, int end,
		OrderByComparator<RegistrationForm> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the registration forms from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of registration forms.
	*
	* @return the number of registration forms
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static RegistrationFormPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<RegistrationFormPersistence, RegistrationFormPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(RegistrationFormPersistence.class);

		ServiceTracker<RegistrationFormPersistence, RegistrationFormPersistence> serviceTracker =
			new ServiceTracker<RegistrationFormPersistence, RegistrationFormPersistence>(bundle.getBundleContext(),
				RegistrationFormPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}