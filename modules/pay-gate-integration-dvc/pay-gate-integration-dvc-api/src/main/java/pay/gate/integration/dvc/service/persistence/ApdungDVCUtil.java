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

package pay.gate.integration.dvc.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import pay.gate.integration.dvc.model.ApdungDVC;

import java.util.List;

/**
 * The persistence utility for the apdung dvc service. This utility wraps {@link pay.gate.integration.dvc.service.persistence.impl.ApdungDVCPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ApdungDVCPersistence
 * @see pay.gate.integration.dvc.service.persistence.impl.ApdungDVCPersistenceImpl
 * @generated
 */
@ProviderType
public class ApdungDVCUtil {
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
	public static void clearCache(ApdungDVC apdungDVC) {
		getPersistence().clearCache(apdungDVC);
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
	public static List<ApdungDVC> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<ApdungDVC> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<ApdungDVC> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<ApdungDVC> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static ApdungDVC update(ApdungDVC apdungDVC) {
		return getPersistence().update(apdungDVC);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static ApdungDVC update(ApdungDVC apdungDVC,
		ServiceContext serviceContext) {
		return getPersistence().update(apdungDVC, serviceContext);
	}

	/**
	* Returns all the apdung dvcs where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching apdung dvcs
	*/
	public static List<ApdungDVC> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the apdung dvcs where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ApdungDVCModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of apdung dvcs
	* @param end the upper bound of the range of apdung dvcs (not inclusive)
	* @return the range of matching apdung dvcs
	*/
	public static List<ApdungDVC> findByUuid(String uuid, int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the apdung dvcs where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ApdungDVCModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of apdung dvcs
	* @param end the upper bound of the range of apdung dvcs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching apdung dvcs
	*/
	public static List<ApdungDVC> findByUuid(String uuid, int start, int end,
		OrderByComparator<ApdungDVC> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the apdung dvcs where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ApdungDVCModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of apdung dvcs
	* @param end the upper bound of the range of apdung dvcs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching apdung dvcs
	*/
	public static List<ApdungDVC> findByUuid(String uuid, int start, int end,
		OrderByComparator<ApdungDVC> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first apdung dvc in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching apdung dvc
	* @throws NoSuchApdungDVCException if a matching apdung dvc could not be found
	*/
	public static ApdungDVC findByUuid_First(String uuid,
		OrderByComparator<ApdungDVC> orderByComparator)
		throws pay.gate.integration.dvc.exception.NoSuchApdungDVCException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first apdung dvc in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching apdung dvc, or <code>null</code> if a matching apdung dvc could not be found
	*/
	public static ApdungDVC fetchByUuid_First(String uuid,
		OrderByComparator<ApdungDVC> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last apdung dvc in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching apdung dvc
	* @throws NoSuchApdungDVCException if a matching apdung dvc could not be found
	*/
	public static ApdungDVC findByUuid_Last(String uuid,
		OrderByComparator<ApdungDVC> orderByComparator)
		throws pay.gate.integration.dvc.exception.NoSuchApdungDVCException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last apdung dvc in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching apdung dvc, or <code>null</code> if a matching apdung dvc could not be found
	*/
	public static ApdungDVC fetchByUuid_Last(String uuid,
		OrderByComparator<ApdungDVC> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the apdung dvcs before and after the current apdung dvc in the ordered set where uuid = &#63;.
	*
	* @param apdungDVCId the primary key of the current apdung dvc
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next apdung dvc
	* @throws NoSuchApdungDVCException if a apdung dvc with the primary key could not be found
	*/
	public static ApdungDVC[] findByUuid_PrevAndNext(long apdungDVCId,
		String uuid, OrderByComparator<ApdungDVC> orderByComparator)
		throws pay.gate.integration.dvc.exception.NoSuchApdungDVCException {
		return getPersistence()
				   .findByUuid_PrevAndNext(apdungDVCId, uuid, orderByComparator);
	}

	/**
	* Removes all the apdung dvcs where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of apdung dvcs where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching apdung dvcs
	*/
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the apdung dvc where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchApdungDVCException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching apdung dvc
	* @throws NoSuchApdungDVCException if a matching apdung dvc could not be found
	*/
	public static ApdungDVC findByUUID_G(String uuid, long groupId)
		throws pay.gate.integration.dvc.exception.NoSuchApdungDVCException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the apdung dvc where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching apdung dvc, or <code>null</code> if a matching apdung dvc could not be found
	*/
	public static ApdungDVC fetchByUUID_G(String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the apdung dvc where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching apdung dvc, or <code>null</code> if a matching apdung dvc could not be found
	*/
	public static ApdungDVC fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the apdung dvc where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the apdung dvc that was removed
	*/
	public static ApdungDVC removeByUUID_G(String uuid, long groupId)
		throws pay.gate.integration.dvc.exception.NoSuchApdungDVCException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of apdung dvcs where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching apdung dvcs
	*/
	public static int countByUUID_G(String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the apdung dvcs where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching apdung dvcs
	*/
	public static List<ApdungDVC> findByUuid_C(String uuid, long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	* Returns a range of all the apdung dvcs where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ApdungDVCModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of apdung dvcs
	* @param end the upper bound of the range of apdung dvcs (not inclusive)
	* @return the range of matching apdung dvcs
	*/
	public static List<ApdungDVC> findByUuid_C(String uuid, long companyId,
		int start, int end) {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	* Returns an ordered range of all the apdung dvcs where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ApdungDVCModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of apdung dvcs
	* @param end the upper bound of the range of apdung dvcs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching apdung dvcs
	*/
	public static List<ApdungDVC> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<ApdungDVC> orderByComparator) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the apdung dvcs where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ApdungDVCModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of apdung dvcs
	* @param end the upper bound of the range of apdung dvcs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching apdung dvcs
	*/
	public static List<ApdungDVC> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<ApdungDVC> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first apdung dvc in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching apdung dvc
	* @throws NoSuchApdungDVCException if a matching apdung dvc could not be found
	*/
	public static ApdungDVC findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<ApdungDVC> orderByComparator)
		throws pay.gate.integration.dvc.exception.NoSuchApdungDVCException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first apdung dvc in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching apdung dvc, or <code>null</code> if a matching apdung dvc could not be found
	*/
	public static ApdungDVC fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<ApdungDVC> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last apdung dvc in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching apdung dvc
	* @throws NoSuchApdungDVCException if a matching apdung dvc could not be found
	*/
	public static ApdungDVC findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<ApdungDVC> orderByComparator)
		throws pay.gate.integration.dvc.exception.NoSuchApdungDVCException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last apdung dvc in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching apdung dvc, or <code>null</code> if a matching apdung dvc could not be found
	*/
	public static ApdungDVC fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<ApdungDVC> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the apdung dvcs before and after the current apdung dvc in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param apdungDVCId the primary key of the current apdung dvc
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next apdung dvc
	* @throws NoSuchApdungDVCException if a apdung dvc with the primary key could not be found
	*/
	public static ApdungDVC[] findByUuid_C_PrevAndNext(long apdungDVCId,
		String uuid, long companyId,
		OrderByComparator<ApdungDVC> orderByComparator)
		throws pay.gate.integration.dvc.exception.NoSuchApdungDVCException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(apdungDVCId, uuid, companyId,
			orderByComparator);
	}

	/**
	* Removes all the apdung dvcs where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public static void removeByUuid_C(String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of apdung dvcs where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching apdung dvcs
	*/
	public static int countByUuid_C(String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Returns the apdung dvc where maTTHC = &#63; and maCQTH = &#63; and mucdo = &#63; or throws a {@link NoSuchApdungDVCException} if it could not be found.
	*
	* @param maTTHC the ma tthc
	* @param maCQTH the ma cqth
	* @param mucdo the mucdo
	* @return the matching apdung dvc
	* @throws NoSuchApdungDVCException if a matching apdung dvc could not be found
	*/
	public static ApdungDVC findByF_TTHC_CQTH_MD(String maTTHC, String maCQTH,
		int mucdo)
		throws pay.gate.integration.dvc.exception.NoSuchApdungDVCException {
		return getPersistence().findByF_TTHC_CQTH_MD(maTTHC, maCQTH, mucdo);
	}

	/**
	* Returns the apdung dvc where maTTHC = &#63; and maCQTH = &#63; and mucdo = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param maTTHC the ma tthc
	* @param maCQTH the ma cqth
	* @param mucdo the mucdo
	* @return the matching apdung dvc, or <code>null</code> if a matching apdung dvc could not be found
	*/
	public static ApdungDVC fetchByF_TTHC_CQTH_MD(String maTTHC, String maCQTH,
		int mucdo) {
		return getPersistence().fetchByF_TTHC_CQTH_MD(maTTHC, maCQTH, mucdo);
	}

	/**
	* Returns the apdung dvc where maTTHC = &#63; and maCQTH = &#63; and mucdo = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param maTTHC the ma tthc
	* @param maCQTH the ma cqth
	* @param mucdo the mucdo
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching apdung dvc, or <code>null</code> if a matching apdung dvc could not be found
	*/
	public static ApdungDVC fetchByF_TTHC_CQTH_MD(String maTTHC, String maCQTH,
		int mucdo, boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByF_TTHC_CQTH_MD(maTTHC, maCQTH, mucdo,
			retrieveFromCache);
	}

	/**
	* Removes the apdung dvc where maTTHC = &#63; and maCQTH = &#63; and mucdo = &#63; from the database.
	*
	* @param maTTHC the ma tthc
	* @param maCQTH the ma cqth
	* @param mucdo the mucdo
	* @return the apdung dvc that was removed
	*/
	public static ApdungDVC removeByF_TTHC_CQTH_MD(String maTTHC,
		String maCQTH, int mucdo)
		throws pay.gate.integration.dvc.exception.NoSuchApdungDVCException {
		return getPersistence().removeByF_TTHC_CQTH_MD(maTTHC, maCQTH, mucdo);
	}

	/**
	* Returns the number of apdung dvcs where maTTHC = &#63; and maCQTH = &#63; and mucdo = &#63;.
	*
	* @param maTTHC the ma tthc
	* @param maCQTH the ma cqth
	* @param mucdo the mucdo
	* @return the number of matching apdung dvcs
	*/
	public static int countByF_TTHC_CQTH_MD(String maTTHC, String maCQTH,
		int mucdo) {
		return getPersistence().countByF_TTHC_CQTH_MD(maTTHC, maCQTH, mucdo);
	}

	/**
	* Caches the apdung dvc in the entity cache if it is enabled.
	*
	* @param apdungDVC the apdung dvc
	*/
	public static void cacheResult(ApdungDVC apdungDVC) {
		getPersistence().cacheResult(apdungDVC);
	}

	/**
	* Caches the apdung dvcs in the entity cache if it is enabled.
	*
	* @param apdungDVCs the apdung dvcs
	*/
	public static void cacheResult(List<ApdungDVC> apdungDVCs) {
		getPersistence().cacheResult(apdungDVCs);
	}

	/**
	* Creates a new apdung dvc with the primary key. Does not add the apdung dvc to the database.
	*
	* @param apdungDVCId the primary key for the new apdung dvc
	* @return the new apdung dvc
	*/
	public static ApdungDVC create(long apdungDVCId) {
		return getPersistence().create(apdungDVCId);
	}

	/**
	* Removes the apdung dvc with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param apdungDVCId the primary key of the apdung dvc
	* @return the apdung dvc that was removed
	* @throws NoSuchApdungDVCException if a apdung dvc with the primary key could not be found
	*/
	public static ApdungDVC remove(long apdungDVCId)
		throws pay.gate.integration.dvc.exception.NoSuchApdungDVCException {
		return getPersistence().remove(apdungDVCId);
	}

	public static ApdungDVC updateImpl(ApdungDVC apdungDVC) {
		return getPersistence().updateImpl(apdungDVC);
	}

	/**
	* Returns the apdung dvc with the primary key or throws a {@link NoSuchApdungDVCException} if it could not be found.
	*
	* @param apdungDVCId the primary key of the apdung dvc
	* @return the apdung dvc
	* @throws NoSuchApdungDVCException if a apdung dvc with the primary key could not be found
	*/
	public static ApdungDVC findByPrimaryKey(long apdungDVCId)
		throws pay.gate.integration.dvc.exception.NoSuchApdungDVCException {
		return getPersistence().findByPrimaryKey(apdungDVCId);
	}

	/**
	* Returns the apdung dvc with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param apdungDVCId the primary key of the apdung dvc
	* @return the apdung dvc, or <code>null</code> if a apdung dvc with the primary key could not be found
	*/
	public static ApdungDVC fetchByPrimaryKey(long apdungDVCId) {
		return getPersistence().fetchByPrimaryKey(apdungDVCId);
	}

	public static java.util.Map<java.io.Serializable, ApdungDVC> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the apdung dvcs.
	*
	* @return the apdung dvcs
	*/
	public static List<ApdungDVC> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the apdung dvcs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ApdungDVCModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of apdung dvcs
	* @param end the upper bound of the range of apdung dvcs (not inclusive)
	* @return the range of apdung dvcs
	*/
	public static List<ApdungDVC> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the apdung dvcs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ApdungDVCModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of apdung dvcs
	* @param end the upper bound of the range of apdung dvcs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of apdung dvcs
	*/
	public static List<ApdungDVC> findAll(int start, int end,
		OrderByComparator<ApdungDVC> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the apdung dvcs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ApdungDVCModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of apdung dvcs
	* @param end the upper bound of the range of apdung dvcs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of apdung dvcs
	*/
	public static List<ApdungDVC> findAll(int start, int end,
		OrderByComparator<ApdungDVC> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the apdung dvcs from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of apdung dvcs.
	*
	* @return the number of apdung dvcs
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static ApdungDVCPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<ApdungDVCPersistence, ApdungDVCPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(ApdungDVCPersistence.class);

		ServiceTracker<ApdungDVCPersistence, ApdungDVCPersistence> serviceTracker =
			new ServiceTracker<ApdungDVCPersistence, ApdungDVCPersistence>(bundle.getBundleContext(),
				ApdungDVCPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}