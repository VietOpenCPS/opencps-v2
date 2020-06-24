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

import pay.gate.integration.dvc.model.PhiLePhi;

import java.util.List;

/**
 * The persistence utility for the phi le phi service. This utility wraps {@link pay.gate.integration.dvc.service.persistence.impl.PhiLePhiPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PhiLePhiPersistence
 * @see pay.gate.integration.dvc.service.persistence.impl.PhiLePhiPersistenceImpl
 * @generated
 */
@ProviderType
public class PhiLePhiUtil {
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
	public static void clearCache(PhiLePhi phiLePhi) {
		getPersistence().clearCache(phiLePhi);
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
	public static List<PhiLePhi> findWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<PhiLePhi> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<PhiLePhi> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<PhiLePhi> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static PhiLePhi update(PhiLePhi phiLePhi) {
		return getPersistence().update(phiLePhi);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static PhiLePhi update(PhiLePhi phiLePhi,
		ServiceContext serviceContext) {
		return getPersistence().update(phiLePhi, serviceContext);
	}

	/**
	* Returns all the phi le phis where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching phi le phis
	*/
	public static List<PhiLePhi> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the phi le phis where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PhiLePhiModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of phi le phis
	* @param end the upper bound of the range of phi le phis (not inclusive)
	* @return the range of matching phi le phis
	*/
	public static List<PhiLePhi> findByUuid(String uuid, int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the phi le phis where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PhiLePhiModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of phi le phis
	* @param end the upper bound of the range of phi le phis (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching phi le phis
	*/
	public static List<PhiLePhi> findByUuid(String uuid, int start, int end,
		OrderByComparator<PhiLePhi> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the phi le phis where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PhiLePhiModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of phi le phis
	* @param end the upper bound of the range of phi le phis (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching phi le phis
	*/
	public static List<PhiLePhi> findByUuid(String uuid, int start, int end,
		OrderByComparator<PhiLePhi> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first phi le phi in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching phi le phi
	* @throws NoSuchPhiLePhiException if a matching phi le phi could not be found
	*/
	public static PhiLePhi findByUuid_First(String uuid,
		OrderByComparator<PhiLePhi> orderByComparator)
		throws pay.gate.integration.dvc.exception.NoSuchPhiLePhiException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first phi le phi in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching phi le phi, or <code>null</code> if a matching phi le phi could not be found
	*/
	public static PhiLePhi fetchByUuid_First(String uuid,
		OrderByComparator<PhiLePhi> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last phi le phi in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching phi le phi
	* @throws NoSuchPhiLePhiException if a matching phi le phi could not be found
	*/
	public static PhiLePhi findByUuid_Last(String uuid,
		OrderByComparator<PhiLePhi> orderByComparator)
		throws pay.gate.integration.dvc.exception.NoSuchPhiLePhiException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last phi le phi in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching phi le phi, or <code>null</code> if a matching phi le phi could not be found
	*/
	public static PhiLePhi fetchByUuid_Last(String uuid,
		OrderByComparator<PhiLePhi> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the phi le phis before and after the current phi le phi in the ordered set where uuid = &#63;.
	*
	* @param phiLePhiId the primary key of the current phi le phi
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next phi le phi
	* @throws NoSuchPhiLePhiException if a phi le phi with the primary key could not be found
	*/
	public static PhiLePhi[] findByUuid_PrevAndNext(long phiLePhiId,
		String uuid, OrderByComparator<PhiLePhi> orderByComparator)
		throws pay.gate.integration.dvc.exception.NoSuchPhiLePhiException {
		return getPersistence()
				   .findByUuid_PrevAndNext(phiLePhiId, uuid, orderByComparator);
	}

	/**
	* Removes all the phi le phis where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of phi le phis where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching phi le phis
	*/
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the phi le phi where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchPhiLePhiException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching phi le phi
	* @throws NoSuchPhiLePhiException if a matching phi le phi could not be found
	*/
	public static PhiLePhi findByUUID_G(String uuid, long groupId)
		throws pay.gate.integration.dvc.exception.NoSuchPhiLePhiException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the phi le phi where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching phi le phi, or <code>null</code> if a matching phi le phi could not be found
	*/
	public static PhiLePhi fetchByUUID_G(String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the phi le phi where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching phi le phi, or <code>null</code> if a matching phi le phi could not be found
	*/
	public static PhiLePhi fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the phi le phi where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the phi le phi that was removed
	*/
	public static PhiLePhi removeByUUID_G(String uuid, long groupId)
		throws pay.gate.integration.dvc.exception.NoSuchPhiLePhiException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of phi le phis where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching phi le phis
	*/
	public static int countByUUID_G(String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the phi le phis where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching phi le phis
	*/
	public static List<PhiLePhi> findByUuid_C(String uuid, long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	* Returns a range of all the phi le phis where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PhiLePhiModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of phi le phis
	* @param end the upper bound of the range of phi le phis (not inclusive)
	* @return the range of matching phi le phis
	*/
	public static List<PhiLePhi> findByUuid_C(String uuid, long companyId,
		int start, int end) {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	* Returns an ordered range of all the phi le phis where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PhiLePhiModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of phi le phis
	* @param end the upper bound of the range of phi le phis (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching phi le phis
	*/
	public static List<PhiLePhi> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<PhiLePhi> orderByComparator) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the phi le phis where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PhiLePhiModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of phi le phis
	* @param end the upper bound of the range of phi le phis (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching phi le phis
	*/
	public static List<PhiLePhi> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<PhiLePhi> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first phi le phi in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching phi le phi
	* @throws NoSuchPhiLePhiException if a matching phi le phi could not be found
	*/
	public static PhiLePhi findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<PhiLePhi> orderByComparator)
		throws pay.gate.integration.dvc.exception.NoSuchPhiLePhiException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first phi le phi in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching phi le phi, or <code>null</code> if a matching phi le phi could not be found
	*/
	public static PhiLePhi fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<PhiLePhi> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last phi le phi in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching phi le phi
	* @throws NoSuchPhiLePhiException if a matching phi le phi could not be found
	*/
	public static PhiLePhi findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<PhiLePhi> orderByComparator)
		throws pay.gate.integration.dvc.exception.NoSuchPhiLePhiException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last phi le phi in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching phi le phi, or <code>null</code> if a matching phi le phi could not be found
	*/
	public static PhiLePhi fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<PhiLePhi> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the phi le phis before and after the current phi le phi in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param phiLePhiId the primary key of the current phi le phi
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next phi le phi
	* @throws NoSuchPhiLePhiException if a phi le phi with the primary key could not be found
	*/
	public static PhiLePhi[] findByUuid_C_PrevAndNext(long phiLePhiId,
		String uuid, long companyId,
		OrderByComparator<PhiLePhi> orderByComparator)
		throws pay.gate.integration.dvc.exception.NoSuchPhiLePhiException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(phiLePhiId, uuid, companyId,
			orderByComparator);
	}

	/**
	* Removes all the phi le phis where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public static void removeByUuid_C(String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of phi le phis where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching phi le phis
	*/
	public static int countByUuid_C(String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Returns all the phi le phis where groupId = &#63; and serviceConfigMappingId = &#63;.
	*
	* @param groupId the group ID
	* @param serviceConfigMappingId the service config mapping ID
	* @return the matching phi le phis
	*/
	public static List<PhiLePhi> findByG_SCMID(long groupId,
		long serviceConfigMappingId) {
		return getPersistence().findByG_SCMID(groupId, serviceConfigMappingId);
	}

	/**
	* Returns a range of all the phi le phis where groupId = &#63; and serviceConfigMappingId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PhiLePhiModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param serviceConfigMappingId the service config mapping ID
	* @param start the lower bound of the range of phi le phis
	* @param end the upper bound of the range of phi le phis (not inclusive)
	* @return the range of matching phi le phis
	*/
	public static List<PhiLePhi> findByG_SCMID(long groupId,
		long serviceConfigMappingId, int start, int end) {
		return getPersistence()
				   .findByG_SCMID(groupId, serviceConfigMappingId, start, end);
	}

	/**
	* Returns an ordered range of all the phi le phis where groupId = &#63; and serviceConfigMappingId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PhiLePhiModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param serviceConfigMappingId the service config mapping ID
	* @param start the lower bound of the range of phi le phis
	* @param end the upper bound of the range of phi le phis (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching phi le phis
	*/
	public static List<PhiLePhi> findByG_SCMID(long groupId,
		long serviceConfigMappingId, int start, int end,
		OrderByComparator<PhiLePhi> orderByComparator) {
		return getPersistence()
				   .findByG_SCMID(groupId, serviceConfigMappingId, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the phi le phis where groupId = &#63; and serviceConfigMappingId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PhiLePhiModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param serviceConfigMappingId the service config mapping ID
	* @param start the lower bound of the range of phi le phis
	* @param end the upper bound of the range of phi le phis (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching phi le phis
	*/
	public static List<PhiLePhi> findByG_SCMID(long groupId,
		long serviceConfigMappingId, int start, int end,
		OrderByComparator<PhiLePhi> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findByG_SCMID(groupId, serviceConfigMappingId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first phi le phi in the ordered set where groupId = &#63; and serviceConfigMappingId = &#63;.
	*
	* @param groupId the group ID
	* @param serviceConfigMappingId the service config mapping ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching phi le phi
	* @throws NoSuchPhiLePhiException if a matching phi le phi could not be found
	*/
	public static PhiLePhi findByG_SCMID_First(long groupId,
		long serviceConfigMappingId,
		OrderByComparator<PhiLePhi> orderByComparator)
		throws pay.gate.integration.dvc.exception.NoSuchPhiLePhiException {
		return getPersistence()
				   .findByG_SCMID_First(groupId, serviceConfigMappingId,
			orderByComparator);
	}

	/**
	* Returns the first phi le phi in the ordered set where groupId = &#63; and serviceConfigMappingId = &#63;.
	*
	* @param groupId the group ID
	* @param serviceConfigMappingId the service config mapping ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching phi le phi, or <code>null</code> if a matching phi le phi could not be found
	*/
	public static PhiLePhi fetchByG_SCMID_First(long groupId,
		long serviceConfigMappingId,
		OrderByComparator<PhiLePhi> orderByComparator) {
		return getPersistence()
				   .fetchByG_SCMID_First(groupId, serviceConfigMappingId,
			orderByComparator);
	}

	/**
	* Returns the last phi le phi in the ordered set where groupId = &#63; and serviceConfigMappingId = &#63;.
	*
	* @param groupId the group ID
	* @param serviceConfigMappingId the service config mapping ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching phi le phi
	* @throws NoSuchPhiLePhiException if a matching phi le phi could not be found
	*/
	public static PhiLePhi findByG_SCMID_Last(long groupId,
		long serviceConfigMappingId,
		OrderByComparator<PhiLePhi> orderByComparator)
		throws pay.gate.integration.dvc.exception.NoSuchPhiLePhiException {
		return getPersistence()
				   .findByG_SCMID_Last(groupId, serviceConfigMappingId,
			orderByComparator);
	}

	/**
	* Returns the last phi le phi in the ordered set where groupId = &#63; and serviceConfigMappingId = &#63;.
	*
	* @param groupId the group ID
	* @param serviceConfigMappingId the service config mapping ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching phi le phi, or <code>null</code> if a matching phi le phi could not be found
	*/
	public static PhiLePhi fetchByG_SCMID_Last(long groupId,
		long serviceConfigMappingId,
		OrderByComparator<PhiLePhi> orderByComparator) {
		return getPersistence()
				   .fetchByG_SCMID_Last(groupId, serviceConfigMappingId,
			orderByComparator);
	}

	/**
	* Returns the phi le phis before and after the current phi le phi in the ordered set where groupId = &#63; and serviceConfigMappingId = &#63;.
	*
	* @param phiLePhiId the primary key of the current phi le phi
	* @param groupId the group ID
	* @param serviceConfigMappingId the service config mapping ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next phi le phi
	* @throws NoSuchPhiLePhiException if a phi le phi with the primary key could not be found
	*/
	public static PhiLePhi[] findByG_SCMID_PrevAndNext(long phiLePhiId,
		long groupId, long serviceConfigMappingId,
		OrderByComparator<PhiLePhi> orderByComparator)
		throws pay.gate.integration.dvc.exception.NoSuchPhiLePhiException {
		return getPersistence()
				   .findByG_SCMID_PrevAndNext(phiLePhiId, groupId,
			serviceConfigMappingId, orderByComparator);
	}

	/**
	* Removes all the phi le phis where groupId = &#63; and serviceConfigMappingId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param serviceConfigMappingId the service config mapping ID
	*/
	public static void removeByG_SCMID(long groupId, long serviceConfigMappingId) {
		getPersistence().removeByG_SCMID(groupId, serviceConfigMappingId);
	}

	/**
	* Returns the number of phi le phis where groupId = &#63; and serviceConfigMappingId = &#63;.
	*
	* @param groupId the group ID
	* @param serviceConfigMappingId the service config mapping ID
	* @return the number of matching phi le phis
	*/
	public static int countByG_SCMID(long groupId, long serviceConfigMappingId) {
		return getPersistence().countByG_SCMID(groupId, serviceConfigMappingId);
	}

	/**
	* Caches the phi le phi in the entity cache if it is enabled.
	*
	* @param phiLePhi the phi le phi
	*/
	public static void cacheResult(PhiLePhi phiLePhi) {
		getPersistence().cacheResult(phiLePhi);
	}

	/**
	* Caches the phi le phis in the entity cache if it is enabled.
	*
	* @param phiLePhis the phi le phis
	*/
	public static void cacheResult(List<PhiLePhi> phiLePhis) {
		getPersistence().cacheResult(phiLePhis);
	}

	/**
	* Creates a new phi le phi with the primary key. Does not add the phi le phi to the database.
	*
	* @param phiLePhiId the primary key for the new phi le phi
	* @return the new phi le phi
	*/
	public static PhiLePhi create(long phiLePhiId) {
		return getPersistence().create(phiLePhiId);
	}

	/**
	* Removes the phi le phi with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param phiLePhiId the primary key of the phi le phi
	* @return the phi le phi that was removed
	* @throws NoSuchPhiLePhiException if a phi le phi with the primary key could not be found
	*/
	public static PhiLePhi remove(long phiLePhiId)
		throws pay.gate.integration.dvc.exception.NoSuchPhiLePhiException {
		return getPersistence().remove(phiLePhiId);
	}

	public static PhiLePhi updateImpl(PhiLePhi phiLePhi) {
		return getPersistence().updateImpl(phiLePhi);
	}

	/**
	* Returns the phi le phi with the primary key or throws a {@link NoSuchPhiLePhiException} if it could not be found.
	*
	* @param phiLePhiId the primary key of the phi le phi
	* @return the phi le phi
	* @throws NoSuchPhiLePhiException if a phi le phi with the primary key could not be found
	*/
	public static PhiLePhi findByPrimaryKey(long phiLePhiId)
		throws pay.gate.integration.dvc.exception.NoSuchPhiLePhiException {
		return getPersistence().findByPrimaryKey(phiLePhiId);
	}

	/**
	* Returns the phi le phi with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param phiLePhiId the primary key of the phi le phi
	* @return the phi le phi, or <code>null</code> if a phi le phi with the primary key could not be found
	*/
	public static PhiLePhi fetchByPrimaryKey(long phiLePhiId) {
		return getPersistence().fetchByPrimaryKey(phiLePhiId);
	}

	public static java.util.Map<java.io.Serializable, PhiLePhi> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the phi le phis.
	*
	* @return the phi le phis
	*/
	public static List<PhiLePhi> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the phi le phis.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PhiLePhiModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of phi le phis
	* @param end the upper bound of the range of phi le phis (not inclusive)
	* @return the range of phi le phis
	*/
	public static List<PhiLePhi> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the phi le phis.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PhiLePhiModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of phi le phis
	* @param end the upper bound of the range of phi le phis (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of phi le phis
	*/
	public static List<PhiLePhi> findAll(int start, int end,
		OrderByComparator<PhiLePhi> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the phi le phis.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PhiLePhiModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of phi le phis
	* @param end the upper bound of the range of phi le phis (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of phi le phis
	*/
	public static List<PhiLePhi> findAll(int start, int end,
		OrderByComparator<PhiLePhi> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the phi le phis from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of phi le phis.
	*
	* @return the number of phi le phis
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static PhiLePhiPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<PhiLePhiPersistence, PhiLePhiPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(PhiLePhiPersistence.class);

		ServiceTracker<PhiLePhiPersistence, PhiLePhiPersistence> serviceTracker = new ServiceTracker<PhiLePhiPersistence, PhiLePhiPersistence>(bundle.getBundleContext(),
				PhiLePhiPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}