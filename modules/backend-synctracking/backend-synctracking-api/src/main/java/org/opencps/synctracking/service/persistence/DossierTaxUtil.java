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

package org.opencps.synctracking.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.opencps.synctracking.model.DossierTax;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the dossier tax service. This utility wraps {@link org.opencps.synctracking.service.persistence.impl.DossierTaxPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author duongnt
 * @see DossierTaxPersistence
 * @see org.opencps.synctracking.service.persistence.impl.DossierTaxPersistenceImpl
 * @generated
 */
@ProviderType
public class DossierTaxUtil {
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
	public static void clearCache(DossierTax dossierTax) {
		getPersistence().clearCache(dossierTax);
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
	public static List<DossierTax> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<DossierTax> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<DossierTax> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<DossierTax> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static DossierTax update(DossierTax dossierTax) {
		return getPersistence().update(dossierTax);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static DossierTax update(DossierTax dossierTax,
		ServiceContext serviceContext) {
		return getPersistence().update(dossierTax, serviceContext);
	}

	/**
	* Returns all the dossier taxs where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching dossier taxs
	*/
	public static List<DossierTax> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the dossier taxs where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierTaxModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of dossier taxs
	* @param end the upper bound of the range of dossier taxs (not inclusive)
	* @return the range of matching dossier taxs
	*/
	public static List<DossierTax> findByUuid(String uuid, int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the dossier taxs where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierTaxModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of dossier taxs
	* @param end the upper bound of the range of dossier taxs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossier taxs
	*/
	public static List<DossierTax> findByUuid(String uuid, int start, int end,
		OrderByComparator<DossierTax> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the dossier taxs where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierTaxModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of dossier taxs
	* @param end the upper bound of the range of dossier taxs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossier taxs
	*/
	public static List<DossierTax> findByUuid(String uuid, int start, int end,
		OrderByComparator<DossierTax> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first dossier tax in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier tax
	* @throws NoSuchDossierTaxException if a matching dossier tax could not be found
	*/
	public static DossierTax findByUuid_First(String uuid,
		OrderByComparator<DossierTax> orderByComparator)
		throws org.opencps.synctracking.exception.NoSuchDossierTaxException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first dossier tax in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier tax, or <code>null</code> if a matching dossier tax could not be found
	*/
	public static DossierTax fetchByUuid_First(String uuid,
		OrderByComparator<DossierTax> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last dossier tax in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier tax
	* @throws NoSuchDossierTaxException if a matching dossier tax could not be found
	*/
	public static DossierTax findByUuid_Last(String uuid,
		OrderByComparator<DossierTax> orderByComparator)
		throws org.opencps.synctracking.exception.NoSuchDossierTaxException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last dossier tax in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier tax, or <code>null</code> if a matching dossier tax could not be found
	*/
	public static DossierTax fetchByUuid_Last(String uuid,
		OrderByComparator<DossierTax> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the dossier taxs before and after the current dossier tax in the ordered set where uuid = &#63;.
	*
	* @param taxId the primary key of the current dossier tax
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dossier tax
	* @throws NoSuchDossierTaxException if a dossier tax with the primary key could not be found
	*/
	public static DossierTax[] findByUuid_PrevAndNext(long taxId, String uuid,
		OrderByComparator<DossierTax> orderByComparator)
		throws org.opencps.synctracking.exception.NoSuchDossierTaxException {
		return getPersistence()
				   .findByUuid_PrevAndNext(taxId, uuid, orderByComparator);
	}

	/**
	* Removes all the dossier taxs where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of dossier taxs where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching dossier taxs
	*/
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the dossier tax where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchDossierTaxException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching dossier tax
	* @throws NoSuchDossierTaxException if a matching dossier tax could not be found
	*/
	public static DossierTax findByUUID_G(String uuid, long groupId)
		throws org.opencps.synctracking.exception.NoSuchDossierTaxException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the dossier tax where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching dossier tax, or <code>null</code> if a matching dossier tax could not be found
	*/
	public static DossierTax fetchByUUID_G(String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the dossier tax where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching dossier tax, or <code>null</code> if a matching dossier tax could not be found
	*/
	public static DossierTax fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the dossier tax where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the dossier tax that was removed
	*/
	public static DossierTax removeByUUID_G(String uuid, long groupId)
		throws org.opencps.synctracking.exception.NoSuchDossierTaxException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of dossier taxs where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching dossier taxs
	*/
	public static int countByUUID_G(String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the dossier taxs where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching dossier taxs
	*/
	public static List<DossierTax> findByUuid_C(String uuid, long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	* Returns a range of all the dossier taxs where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierTaxModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of dossier taxs
	* @param end the upper bound of the range of dossier taxs (not inclusive)
	* @return the range of matching dossier taxs
	*/
	public static List<DossierTax> findByUuid_C(String uuid, long companyId,
		int start, int end) {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	* Returns an ordered range of all the dossier taxs where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierTaxModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of dossier taxs
	* @param end the upper bound of the range of dossier taxs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossier taxs
	*/
	public static List<DossierTax> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<DossierTax> orderByComparator) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the dossier taxs where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierTaxModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of dossier taxs
	* @param end the upper bound of the range of dossier taxs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossier taxs
	*/
	public static List<DossierTax> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<DossierTax> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first dossier tax in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier tax
	* @throws NoSuchDossierTaxException if a matching dossier tax could not be found
	*/
	public static DossierTax findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<DossierTax> orderByComparator)
		throws org.opencps.synctracking.exception.NoSuchDossierTaxException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first dossier tax in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier tax, or <code>null</code> if a matching dossier tax could not be found
	*/
	public static DossierTax fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<DossierTax> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last dossier tax in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier tax
	* @throws NoSuchDossierTaxException if a matching dossier tax could not be found
	*/
	public static DossierTax findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<DossierTax> orderByComparator)
		throws org.opencps.synctracking.exception.NoSuchDossierTaxException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last dossier tax in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier tax, or <code>null</code> if a matching dossier tax could not be found
	*/
	public static DossierTax fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<DossierTax> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the dossier taxs before and after the current dossier tax in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param taxId the primary key of the current dossier tax
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dossier tax
	* @throws NoSuchDossierTaxException if a dossier tax with the primary key could not be found
	*/
	public static DossierTax[] findByUuid_C_PrevAndNext(long taxId,
		String uuid, long companyId,
		OrderByComparator<DossierTax> orderByComparator)
		throws org.opencps.synctracking.exception.NoSuchDossierTaxException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(taxId, uuid, companyId,
			orderByComparator);
	}

	/**
	* Removes all the dossier taxs where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public static void removeByUuid_C(String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of dossier taxs where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching dossier taxs
	*/
	public static int countByUuid_C(String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Returns the dossier tax where dossierNo = &#63; and maSoThue = &#63; and soQuyetDinh = &#63; or throws a {@link NoSuchDossierTaxException} if it could not be found.
	*
	* @param dossierNo the dossier no
	* @param maSoThue the ma so thue
	* @param soQuyetDinh the so quyet dinh
	* @return the matching dossier tax
	* @throws NoSuchDossierTaxException if a matching dossier tax could not be found
	*/
	public static DossierTax findByF_DMS(String dossierNo, String maSoThue,
		String soQuyetDinh)
		throws org.opencps.synctracking.exception.NoSuchDossierTaxException {
		return getPersistence().findByF_DMS(dossierNo, maSoThue, soQuyetDinh);
	}

	/**
	* Returns the dossier tax where dossierNo = &#63; and maSoThue = &#63; and soQuyetDinh = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param dossierNo the dossier no
	* @param maSoThue the ma so thue
	* @param soQuyetDinh the so quyet dinh
	* @return the matching dossier tax, or <code>null</code> if a matching dossier tax could not be found
	*/
	public static DossierTax fetchByF_DMS(String dossierNo, String maSoThue,
		String soQuyetDinh) {
		return getPersistence().fetchByF_DMS(dossierNo, maSoThue, soQuyetDinh);
	}

	/**
	* Returns the dossier tax where dossierNo = &#63; and maSoThue = &#63; and soQuyetDinh = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param dossierNo the dossier no
	* @param maSoThue the ma so thue
	* @param soQuyetDinh the so quyet dinh
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching dossier tax, or <code>null</code> if a matching dossier tax could not be found
	*/
	public static DossierTax fetchByF_DMS(String dossierNo, String maSoThue,
		String soQuyetDinh, boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByF_DMS(dossierNo, maSoThue, soQuyetDinh,
			retrieveFromCache);
	}

	/**
	* Removes the dossier tax where dossierNo = &#63; and maSoThue = &#63; and soQuyetDinh = &#63; from the database.
	*
	* @param dossierNo the dossier no
	* @param maSoThue the ma so thue
	* @param soQuyetDinh the so quyet dinh
	* @return the dossier tax that was removed
	*/
	public static DossierTax removeByF_DMS(String dossierNo, String maSoThue,
		String soQuyetDinh)
		throws org.opencps.synctracking.exception.NoSuchDossierTaxException {
		return getPersistence().removeByF_DMS(dossierNo, maSoThue, soQuyetDinh);
	}

	/**
	* Returns the number of dossier taxs where dossierNo = &#63; and maSoThue = &#63; and soQuyetDinh = &#63;.
	*
	* @param dossierNo the dossier no
	* @param maSoThue the ma so thue
	* @param soQuyetDinh the so quyet dinh
	* @return the number of matching dossier taxs
	*/
	public static int countByF_DMS(String dossierNo, String maSoThue,
		String soQuyetDinh) {
		return getPersistence().countByF_DMS(dossierNo, maSoThue, soQuyetDinh);
	}

	/**
	* Caches the dossier tax in the entity cache if it is enabled.
	*
	* @param dossierTax the dossier tax
	*/
	public static void cacheResult(DossierTax dossierTax) {
		getPersistence().cacheResult(dossierTax);
	}

	/**
	* Caches the dossier taxs in the entity cache if it is enabled.
	*
	* @param dossierTaxs the dossier taxs
	*/
	public static void cacheResult(List<DossierTax> dossierTaxs) {
		getPersistence().cacheResult(dossierTaxs);
	}

	/**
	* Creates a new dossier tax with the primary key. Does not add the dossier tax to the database.
	*
	* @param taxId the primary key for the new dossier tax
	* @return the new dossier tax
	*/
	public static DossierTax create(long taxId) {
		return getPersistence().create(taxId);
	}

	/**
	* Removes the dossier tax with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param taxId the primary key of the dossier tax
	* @return the dossier tax that was removed
	* @throws NoSuchDossierTaxException if a dossier tax with the primary key could not be found
	*/
	public static DossierTax remove(long taxId)
		throws org.opencps.synctracking.exception.NoSuchDossierTaxException {
		return getPersistence().remove(taxId);
	}

	public static DossierTax updateImpl(DossierTax dossierTax) {
		return getPersistence().updateImpl(dossierTax);
	}

	/**
	* Returns the dossier tax with the primary key or throws a {@link NoSuchDossierTaxException} if it could not be found.
	*
	* @param taxId the primary key of the dossier tax
	* @return the dossier tax
	* @throws NoSuchDossierTaxException if a dossier tax with the primary key could not be found
	*/
	public static DossierTax findByPrimaryKey(long taxId)
		throws org.opencps.synctracking.exception.NoSuchDossierTaxException {
		return getPersistence().findByPrimaryKey(taxId);
	}

	/**
	* Returns the dossier tax with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param taxId the primary key of the dossier tax
	* @return the dossier tax, or <code>null</code> if a dossier tax with the primary key could not be found
	*/
	public static DossierTax fetchByPrimaryKey(long taxId) {
		return getPersistence().fetchByPrimaryKey(taxId);
	}

	public static java.util.Map<java.io.Serializable, DossierTax> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the dossier taxs.
	*
	* @return the dossier taxs
	*/
	public static List<DossierTax> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the dossier taxs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierTaxModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dossier taxs
	* @param end the upper bound of the range of dossier taxs (not inclusive)
	* @return the range of dossier taxs
	*/
	public static List<DossierTax> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the dossier taxs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierTaxModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dossier taxs
	* @param end the upper bound of the range of dossier taxs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of dossier taxs
	*/
	public static List<DossierTax> findAll(int start, int end,
		OrderByComparator<DossierTax> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the dossier taxs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierTaxModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dossier taxs
	* @param end the upper bound of the range of dossier taxs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of dossier taxs
	*/
	public static List<DossierTax> findAll(int start, int end,
		OrderByComparator<DossierTax> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the dossier taxs from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of dossier taxs.
	*
	* @return the number of dossier taxs
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static DossierTaxPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<DossierTaxPersistence, DossierTaxPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(DossierTaxPersistence.class);

		ServiceTracker<DossierTaxPersistence, DossierTaxPersistence> serviceTracker =
			new ServiceTracker<DossierTaxPersistence, DossierTaxPersistence>(bundle.getBundleContext(),
				DossierTaxPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}