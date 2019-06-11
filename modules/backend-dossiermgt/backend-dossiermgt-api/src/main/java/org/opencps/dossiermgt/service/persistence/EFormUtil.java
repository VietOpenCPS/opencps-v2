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

import org.opencps.dossiermgt.model.EForm;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the e form service. This utility wraps {@link org.opencps.dossiermgt.service.persistence.impl.EFormPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author huymq
 * @see EFormPersistence
 * @see org.opencps.dossiermgt.service.persistence.impl.EFormPersistenceImpl
 * @generated
 */
@ProviderType
public class EFormUtil {
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
	public static void clearCache(EForm eForm) {
		getPersistence().clearCache(eForm);
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
	public static List<EForm> findWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<EForm> findWithDynamicQuery(DynamicQuery dynamicQuery,
		int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<EForm> findWithDynamicQuery(DynamicQuery dynamicQuery,
		int start, int end, OrderByComparator<EForm> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static EForm update(EForm eForm) {
		return getPersistence().update(eForm);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static EForm update(EForm eForm, ServiceContext serviceContext) {
		return getPersistence().update(eForm, serviceContext);
	}

	/**
	* Returns all the e forms where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching e forms
	*/
	public static List<EForm> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

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
	public static List<EForm> findByUuid(String uuid, int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

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
	public static List<EForm> findByUuid(String uuid, int start, int end,
		OrderByComparator<EForm> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

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
	public static List<EForm> findByUuid(String uuid, int start, int end,
		OrderByComparator<EForm> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first e form in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching e form
	* @throws NoSuchEFormException if a matching e form could not be found
	*/
	public static EForm findByUuid_First(String uuid,
		OrderByComparator<EForm> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchEFormException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first e form in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching e form, or <code>null</code> if a matching e form could not be found
	*/
	public static EForm fetchByUuid_First(String uuid,
		OrderByComparator<EForm> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last e form in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching e form
	* @throws NoSuchEFormException if a matching e form could not be found
	*/
	public static EForm findByUuid_Last(String uuid,
		OrderByComparator<EForm> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchEFormException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last e form in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching e form, or <code>null</code> if a matching e form could not be found
	*/
	public static EForm fetchByUuid_Last(String uuid,
		OrderByComparator<EForm> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the e forms before and after the current e form in the ordered set where uuid = &#63;.
	*
	* @param eFormId the primary key of the current e form
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next e form
	* @throws NoSuchEFormException if a e form with the primary key could not be found
	*/
	public static EForm[] findByUuid_PrevAndNext(long eFormId, String uuid,
		OrderByComparator<EForm> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchEFormException {
		return getPersistence()
				   .findByUuid_PrevAndNext(eFormId, uuid, orderByComparator);
	}

	/**
	* Removes all the e forms where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of e forms where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching e forms
	*/
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the e form where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchEFormException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching e form
	* @throws NoSuchEFormException if a matching e form could not be found
	*/
	public static EForm findByUUID_G(String uuid, long groupId)
		throws org.opencps.dossiermgt.exception.NoSuchEFormException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the e form where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching e form, or <code>null</code> if a matching e form could not be found
	*/
	public static EForm fetchByUUID_G(String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the e form where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching e form, or <code>null</code> if a matching e form could not be found
	*/
	public static EForm fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the e form where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the e form that was removed
	*/
	public static EForm removeByUUID_G(String uuid, long groupId)
		throws org.opencps.dossiermgt.exception.NoSuchEFormException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of e forms where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching e forms
	*/
	public static int countByUUID_G(String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the e forms where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching e forms
	*/
	public static List<EForm> findByUuid_C(String uuid, long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

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
	public static List<EForm> findByUuid_C(String uuid, long companyId,
		int start, int end) {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

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
	public static List<EForm> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<EForm> orderByComparator) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

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
	public static List<EForm> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<EForm> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first e form in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching e form
	* @throws NoSuchEFormException if a matching e form could not be found
	*/
	public static EForm findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<EForm> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchEFormException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first e form in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching e form, or <code>null</code> if a matching e form could not be found
	*/
	public static EForm fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<EForm> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last e form in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching e form
	* @throws NoSuchEFormException if a matching e form could not be found
	*/
	public static EForm findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<EForm> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchEFormException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last e form in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching e form, or <code>null</code> if a matching e form could not be found
	*/
	public static EForm fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<EForm> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

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
	public static EForm[] findByUuid_C_PrevAndNext(long eFormId, String uuid,
		long companyId, OrderByComparator<EForm> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchEFormException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(eFormId, uuid, companyId,
			orderByComparator);
	}

	/**
	* Removes all the e forms where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public static void removeByUuid_C(String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of e forms where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching e forms
	*/
	public static int countByUuid_C(String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Returns all the e forms where groupId = &#63; and state = &#63;.
	*
	* @param groupId the group ID
	* @param state the state
	* @return the matching e forms
	*/
	public static List<EForm> findByF_GID_STATE(long groupId, int state) {
		return getPersistence().findByF_GID_STATE(groupId, state);
	}

	/**
	* Returns a range of all the e forms where groupId = &#63; and state = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EFormModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param state the state
	* @param start the lower bound of the range of e forms
	* @param end the upper bound of the range of e forms (not inclusive)
	* @return the range of matching e forms
	*/
	public static List<EForm> findByF_GID_STATE(long groupId, int state,
		int start, int end) {
		return getPersistence().findByF_GID_STATE(groupId, state, start, end);
	}

	/**
	* Returns an ordered range of all the e forms where groupId = &#63; and state = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EFormModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param state the state
	* @param start the lower bound of the range of e forms
	* @param end the upper bound of the range of e forms (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching e forms
	*/
	public static List<EForm> findByF_GID_STATE(long groupId, int state,
		int start, int end, OrderByComparator<EForm> orderByComparator) {
		return getPersistence()
				   .findByF_GID_STATE(groupId, state, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the e forms where groupId = &#63; and state = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EFormModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param state the state
	* @param start the lower bound of the range of e forms
	* @param end the upper bound of the range of e forms (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching e forms
	*/
	public static List<EForm> findByF_GID_STATE(long groupId, int state,
		int start, int end, OrderByComparator<EForm> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByF_GID_STATE(groupId, state, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first e form in the ordered set where groupId = &#63; and state = &#63;.
	*
	* @param groupId the group ID
	* @param state the state
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching e form
	* @throws NoSuchEFormException if a matching e form could not be found
	*/
	public static EForm findByF_GID_STATE_First(long groupId, int state,
		OrderByComparator<EForm> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchEFormException {
		return getPersistence()
				   .findByF_GID_STATE_First(groupId, state, orderByComparator);
	}

	/**
	* Returns the first e form in the ordered set where groupId = &#63; and state = &#63;.
	*
	* @param groupId the group ID
	* @param state the state
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching e form, or <code>null</code> if a matching e form could not be found
	*/
	public static EForm fetchByF_GID_STATE_First(long groupId, int state,
		OrderByComparator<EForm> orderByComparator) {
		return getPersistence()
				   .fetchByF_GID_STATE_First(groupId, state, orderByComparator);
	}

	/**
	* Returns the last e form in the ordered set where groupId = &#63; and state = &#63;.
	*
	* @param groupId the group ID
	* @param state the state
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching e form
	* @throws NoSuchEFormException if a matching e form could not be found
	*/
	public static EForm findByF_GID_STATE_Last(long groupId, int state,
		OrderByComparator<EForm> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchEFormException {
		return getPersistence()
				   .findByF_GID_STATE_Last(groupId, state, orderByComparator);
	}

	/**
	* Returns the last e form in the ordered set where groupId = &#63; and state = &#63;.
	*
	* @param groupId the group ID
	* @param state the state
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching e form, or <code>null</code> if a matching e form could not be found
	*/
	public static EForm fetchByF_GID_STATE_Last(long groupId, int state,
		OrderByComparator<EForm> orderByComparator) {
		return getPersistence()
				   .fetchByF_GID_STATE_Last(groupId, state, orderByComparator);
	}

	/**
	* Returns the e forms before and after the current e form in the ordered set where groupId = &#63; and state = &#63;.
	*
	* @param eFormId the primary key of the current e form
	* @param groupId the group ID
	* @param state the state
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next e form
	* @throws NoSuchEFormException if a e form with the primary key could not be found
	*/
	public static EForm[] findByF_GID_STATE_PrevAndNext(long eFormId,
		long groupId, int state, OrderByComparator<EForm> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchEFormException {
		return getPersistence()
				   .findByF_GID_STATE_PrevAndNext(eFormId, groupId, state,
			orderByComparator);
	}

	/**
	* Returns all the e forms where groupId = &#63; and state = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EFormModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param states the states
	* @return the matching e forms
	*/
	public static List<EForm> findByF_GID_STATE(long groupId, int[] states) {
		return getPersistence().findByF_GID_STATE(groupId, states);
	}

	/**
	* Returns a range of all the e forms where groupId = &#63; and state = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EFormModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param states the states
	* @param start the lower bound of the range of e forms
	* @param end the upper bound of the range of e forms (not inclusive)
	* @return the range of matching e forms
	*/
	public static List<EForm> findByF_GID_STATE(long groupId, int[] states,
		int start, int end) {
		return getPersistence().findByF_GID_STATE(groupId, states, start, end);
	}

	/**
	* Returns an ordered range of all the e forms where groupId = &#63; and state = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EFormModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param states the states
	* @param start the lower bound of the range of e forms
	* @param end the upper bound of the range of e forms (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching e forms
	*/
	public static List<EForm> findByF_GID_STATE(long groupId, int[] states,
		int start, int end, OrderByComparator<EForm> orderByComparator) {
		return getPersistence()
				   .findByF_GID_STATE(groupId, states, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the e forms where groupId = &#63; and state = &#63;, optionally using the finder cache.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EFormModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param state the state
	* @param start the lower bound of the range of e forms
	* @param end the upper bound of the range of e forms (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching e forms
	*/
	public static List<EForm> findByF_GID_STATE(long groupId, int[] states,
		int start, int end, OrderByComparator<EForm> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByF_GID_STATE(groupId, states, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the e forms where groupId = &#63; and state = &#63; from the database.
	*
	* @param groupId the group ID
	* @param state the state
	*/
	public static void removeByF_GID_STATE(long groupId, int state) {
		getPersistence().removeByF_GID_STATE(groupId, state);
	}

	/**
	* Returns the number of e forms where groupId = &#63; and state = &#63;.
	*
	* @param groupId the group ID
	* @param state the state
	* @return the number of matching e forms
	*/
	public static int countByF_GID_STATE(long groupId, int state) {
		return getPersistence().countByF_GID_STATE(groupId, state);
	}

	/**
	* Returns the number of e forms where groupId = &#63; and state = any &#63;.
	*
	* @param groupId the group ID
	* @param states the states
	* @return the number of matching e forms
	*/
	public static int countByF_GID_STATE(long groupId, int[] states) {
		return getPersistence().countByF_GID_STATE(groupId, states);
	}

	/**
	* Returns all the e forms where groupId = &#63; and serviceCode = &#63;.
	*
	* @param groupId the group ID
	* @param serviceCode the service code
	* @return the matching e forms
	*/
	public static List<EForm> findByF_GID_SC(long groupId, String serviceCode) {
		return getPersistence().findByF_GID_SC(groupId, serviceCode);
	}

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
	public static List<EForm> findByF_GID_SC(long groupId, String serviceCode,
		int start, int end) {
		return getPersistence().findByF_GID_SC(groupId, serviceCode, start, end);
	}

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
	public static List<EForm> findByF_GID_SC(long groupId, String serviceCode,
		int start, int end, OrderByComparator<EForm> orderByComparator) {
		return getPersistence()
				   .findByF_GID_SC(groupId, serviceCode, start, end,
			orderByComparator);
	}

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
	public static List<EForm> findByF_GID_SC(long groupId, String serviceCode,
		int start, int end, OrderByComparator<EForm> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByF_GID_SC(groupId, serviceCode, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first e form in the ordered set where groupId = &#63; and serviceCode = &#63;.
	*
	* @param groupId the group ID
	* @param serviceCode the service code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching e form
	* @throws NoSuchEFormException if a matching e form could not be found
	*/
	public static EForm findByF_GID_SC_First(long groupId, String serviceCode,
		OrderByComparator<EForm> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchEFormException {
		return getPersistence()
				   .findByF_GID_SC_First(groupId, serviceCode, orderByComparator);
	}

	/**
	* Returns the first e form in the ordered set where groupId = &#63; and serviceCode = &#63;.
	*
	* @param groupId the group ID
	* @param serviceCode the service code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching e form, or <code>null</code> if a matching e form could not be found
	*/
	public static EForm fetchByF_GID_SC_First(long groupId, String serviceCode,
		OrderByComparator<EForm> orderByComparator) {
		return getPersistence()
				   .fetchByF_GID_SC_First(groupId, serviceCode,
			orderByComparator);
	}

	/**
	* Returns the last e form in the ordered set where groupId = &#63; and serviceCode = &#63;.
	*
	* @param groupId the group ID
	* @param serviceCode the service code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching e form
	* @throws NoSuchEFormException if a matching e form could not be found
	*/
	public static EForm findByF_GID_SC_Last(long groupId, String serviceCode,
		OrderByComparator<EForm> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchEFormException {
		return getPersistence()
				   .findByF_GID_SC_Last(groupId, serviceCode, orderByComparator);
	}

	/**
	* Returns the last e form in the ordered set where groupId = &#63; and serviceCode = &#63;.
	*
	* @param groupId the group ID
	* @param serviceCode the service code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching e form, or <code>null</code> if a matching e form could not be found
	*/
	public static EForm fetchByF_GID_SC_Last(long groupId, String serviceCode,
		OrderByComparator<EForm> orderByComparator) {
		return getPersistence()
				   .fetchByF_GID_SC_Last(groupId, serviceCode, orderByComparator);
	}

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
	public static EForm[] findByF_GID_SC_PrevAndNext(long eFormId,
		long groupId, String serviceCode,
		OrderByComparator<EForm> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchEFormException {
		return getPersistence()
				   .findByF_GID_SC_PrevAndNext(eFormId, groupId, serviceCode,
			orderByComparator);
	}

	/**
	* Removes all the e forms where groupId = &#63; and serviceCode = &#63; from the database.
	*
	* @param groupId the group ID
	* @param serviceCode the service code
	*/
	public static void removeByF_GID_SC(long groupId, String serviceCode) {
		getPersistence().removeByF_GID_SC(groupId, serviceCode);
	}

	/**
	* Returns the number of e forms where groupId = &#63; and serviceCode = &#63;.
	*
	* @param groupId the group ID
	* @param serviceCode the service code
	* @return the number of matching e forms
	*/
	public static int countByF_GID_SC(long groupId, String serviceCode) {
		return getPersistence().countByF_GID_SC(groupId, serviceCode);
	}

	/**
	* Returns the e form where groupId = &#63; and eFormNo = &#63; or throws a {@link NoSuchEFormException} if it could not be found.
	*
	* @param groupId the group ID
	* @param eFormNo the e form no
	* @return the matching e form
	* @throws NoSuchEFormException if a matching e form could not be found
	*/
	public static EForm findByF_GID_FORM(long groupId, String eFormNo)
		throws org.opencps.dossiermgt.exception.NoSuchEFormException {
		return getPersistence().findByF_GID_FORM(groupId, eFormNo);
	}

	/**
	* Returns the e form where groupId = &#63; and eFormNo = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param eFormNo the e form no
	* @return the matching e form, or <code>null</code> if a matching e form could not be found
	*/
	public static EForm fetchByF_GID_FORM(long groupId, String eFormNo) {
		return getPersistence().fetchByF_GID_FORM(groupId, eFormNo);
	}

	/**
	* Returns the e form where groupId = &#63; and eFormNo = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param eFormNo the e form no
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching e form, or <code>null</code> if a matching e form could not be found
	*/
	public static EForm fetchByF_GID_FORM(long groupId, String eFormNo,
		boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByF_GID_FORM(groupId, eFormNo, retrieveFromCache);
	}

	/**
	* Removes the e form where groupId = &#63; and eFormNo = &#63; from the database.
	*
	* @param groupId the group ID
	* @param eFormNo the e form no
	* @return the e form that was removed
	*/
	public static EForm removeByF_GID_FORM(long groupId, String eFormNo)
		throws org.opencps.dossiermgt.exception.NoSuchEFormException {
		return getPersistence().removeByF_GID_FORM(groupId, eFormNo);
	}

	/**
	* Returns the number of e forms where groupId = &#63; and eFormNo = &#63;.
	*
	* @param groupId the group ID
	* @param eFormNo the e form no
	* @return the number of matching e forms
	*/
	public static int countByF_GID_FORM(long groupId, String eFormNo) {
		return getPersistence().countByF_GID_FORM(groupId, eFormNo);
	}

	/**
	* Caches the e form in the entity cache if it is enabled.
	*
	* @param eForm the e form
	*/
	public static void cacheResult(EForm eForm) {
		getPersistence().cacheResult(eForm);
	}

	/**
	* Caches the e forms in the entity cache if it is enabled.
	*
	* @param eForms the e forms
	*/
	public static void cacheResult(List<EForm> eForms) {
		getPersistence().cacheResult(eForms);
	}

	/**
	* Creates a new e form with the primary key. Does not add the e form to the database.
	*
	* @param eFormId the primary key for the new e form
	* @return the new e form
	*/
	public static EForm create(long eFormId) {
		return getPersistence().create(eFormId);
	}

	/**
	* Removes the e form with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param eFormId the primary key of the e form
	* @return the e form that was removed
	* @throws NoSuchEFormException if a e form with the primary key could not be found
	*/
	public static EForm remove(long eFormId)
		throws org.opencps.dossiermgt.exception.NoSuchEFormException {
		return getPersistence().remove(eFormId);
	}

	public static EForm updateImpl(EForm eForm) {
		return getPersistence().updateImpl(eForm);
	}

	/**
	* Returns the e form with the primary key or throws a {@link NoSuchEFormException} if it could not be found.
	*
	* @param eFormId the primary key of the e form
	* @return the e form
	* @throws NoSuchEFormException if a e form with the primary key could not be found
	*/
	public static EForm findByPrimaryKey(long eFormId)
		throws org.opencps.dossiermgt.exception.NoSuchEFormException {
		return getPersistence().findByPrimaryKey(eFormId);
	}

	/**
	* Returns the e form with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param eFormId the primary key of the e form
	* @return the e form, or <code>null</code> if a e form with the primary key could not be found
	*/
	public static EForm fetchByPrimaryKey(long eFormId) {
		return getPersistence().fetchByPrimaryKey(eFormId);
	}

	public static java.util.Map<java.io.Serializable, EForm> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the e forms.
	*
	* @return the e forms
	*/
	public static List<EForm> findAll() {
		return getPersistence().findAll();
	}

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
	public static List<EForm> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

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
	public static List<EForm> findAll(int start, int end,
		OrderByComparator<EForm> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

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
	public static List<EForm> findAll(int start, int end,
		OrderByComparator<EForm> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the e forms from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of e forms.
	*
	* @return the number of e forms
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static EFormPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<EFormPersistence, EFormPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(EFormPersistence.class);

		ServiceTracker<EFormPersistence, EFormPersistence> serviceTracker = new ServiceTracker<EFormPersistence, EFormPersistence>(bundle.getBundleContext(),
				EFormPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}