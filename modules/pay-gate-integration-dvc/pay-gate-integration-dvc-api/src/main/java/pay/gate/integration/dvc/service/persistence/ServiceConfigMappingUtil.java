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

import pay.gate.integration.dvc.model.ServiceConfigMapping;

import java.util.List;

/**
 * The persistence utility for the service config mapping service. This utility wraps {@link pay.gate.integration.dvc.service.persistence.impl.ServiceConfigMappingPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ServiceConfigMappingPersistence
 * @see pay.gate.integration.dvc.service.persistence.impl.ServiceConfigMappingPersistenceImpl
 * @generated
 */
@ProviderType
public class ServiceConfigMappingUtil {
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
	public static void clearCache(ServiceConfigMapping serviceConfigMapping) {
		getPersistence().clearCache(serviceConfigMapping);
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
	public static List<ServiceConfigMapping> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<ServiceConfigMapping> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<ServiceConfigMapping> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<ServiceConfigMapping> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static ServiceConfigMapping update(
		ServiceConfigMapping serviceConfigMapping) {
		return getPersistence().update(serviceConfigMapping);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static ServiceConfigMapping update(
		ServiceConfigMapping serviceConfigMapping, ServiceContext serviceContext) {
		return getPersistence().update(serviceConfigMapping, serviceContext);
	}

	/**
	* Returns all the service config mappings where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching service config mappings
	*/
	public static List<ServiceConfigMapping> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the service config mappings where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceConfigMappingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of service config mappings
	* @param end the upper bound of the range of service config mappings (not inclusive)
	* @return the range of matching service config mappings
	*/
	public static List<ServiceConfigMapping> findByUuid(String uuid, int start,
		int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the service config mappings where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceConfigMappingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of service config mappings
	* @param end the upper bound of the range of service config mappings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching service config mappings
	*/
	public static List<ServiceConfigMapping> findByUuid(String uuid, int start,
		int end, OrderByComparator<ServiceConfigMapping> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the service config mappings where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceConfigMappingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of service config mappings
	* @param end the upper bound of the range of service config mappings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching service config mappings
	*/
	public static List<ServiceConfigMapping> findByUuid(String uuid, int start,
		int end, OrderByComparator<ServiceConfigMapping> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first service config mapping in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching service config mapping
	* @throws NoSuchServiceConfigMappingException if a matching service config mapping could not be found
	*/
	public static ServiceConfigMapping findByUuid_First(String uuid,
		OrderByComparator<ServiceConfigMapping> orderByComparator)
		throws pay.gate.integration.dvc.exception.NoSuchServiceConfigMappingException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first service config mapping in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching service config mapping, or <code>null</code> if a matching service config mapping could not be found
	*/
	public static ServiceConfigMapping fetchByUuid_First(String uuid,
		OrderByComparator<ServiceConfigMapping> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last service config mapping in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching service config mapping
	* @throws NoSuchServiceConfigMappingException if a matching service config mapping could not be found
	*/
	public static ServiceConfigMapping findByUuid_Last(String uuid,
		OrderByComparator<ServiceConfigMapping> orderByComparator)
		throws pay.gate.integration.dvc.exception.NoSuchServiceConfigMappingException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last service config mapping in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching service config mapping, or <code>null</code> if a matching service config mapping could not be found
	*/
	public static ServiceConfigMapping fetchByUuid_Last(String uuid,
		OrderByComparator<ServiceConfigMapping> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the service config mappings before and after the current service config mapping in the ordered set where uuid = &#63;.
	*
	* @param serviceConfigMappingId the primary key of the current service config mapping
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next service config mapping
	* @throws NoSuchServiceConfigMappingException if a service config mapping with the primary key could not be found
	*/
	public static ServiceConfigMapping[] findByUuid_PrevAndNext(
		long serviceConfigMappingId, String uuid,
		OrderByComparator<ServiceConfigMapping> orderByComparator)
		throws pay.gate.integration.dvc.exception.NoSuchServiceConfigMappingException {
		return getPersistence()
				   .findByUuid_PrevAndNext(serviceConfigMappingId, uuid,
			orderByComparator);
	}

	/**
	* Removes all the service config mappings where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of service config mappings where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching service config mappings
	*/
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the service config mapping where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchServiceConfigMappingException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching service config mapping
	* @throws NoSuchServiceConfigMappingException if a matching service config mapping could not be found
	*/
	public static ServiceConfigMapping findByUUID_G(String uuid, long groupId)
		throws pay.gate.integration.dvc.exception.NoSuchServiceConfigMappingException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the service config mapping where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching service config mapping, or <code>null</code> if a matching service config mapping could not be found
	*/
	public static ServiceConfigMapping fetchByUUID_G(String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the service config mapping where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching service config mapping, or <code>null</code> if a matching service config mapping could not be found
	*/
	public static ServiceConfigMapping fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the service config mapping where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the service config mapping that was removed
	*/
	public static ServiceConfigMapping removeByUUID_G(String uuid, long groupId)
		throws pay.gate.integration.dvc.exception.NoSuchServiceConfigMappingException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of service config mappings where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching service config mappings
	*/
	public static int countByUUID_G(String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the service config mappings where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching service config mappings
	*/
	public static List<ServiceConfigMapping> findByUuid_C(String uuid,
		long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	* Returns a range of all the service config mappings where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceConfigMappingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of service config mappings
	* @param end the upper bound of the range of service config mappings (not inclusive)
	* @return the range of matching service config mappings
	*/
	public static List<ServiceConfigMapping> findByUuid_C(String uuid,
		long companyId, int start, int end) {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	* Returns an ordered range of all the service config mappings where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceConfigMappingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of service config mappings
	* @param end the upper bound of the range of service config mappings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching service config mappings
	*/
	public static List<ServiceConfigMapping> findByUuid_C(String uuid,
		long companyId, int start, int end,
		OrderByComparator<ServiceConfigMapping> orderByComparator) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the service config mappings where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceConfigMappingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of service config mappings
	* @param end the upper bound of the range of service config mappings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching service config mappings
	*/
	public static List<ServiceConfigMapping> findByUuid_C(String uuid,
		long companyId, int start, int end,
		OrderByComparator<ServiceConfigMapping> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first service config mapping in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching service config mapping
	* @throws NoSuchServiceConfigMappingException if a matching service config mapping could not be found
	*/
	public static ServiceConfigMapping findByUuid_C_First(String uuid,
		long companyId,
		OrderByComparator<ServiceConfigMapping> orderByComparator)
		throws pay.gate.integration.dvc.exception.NoSuchServiceConfigMappingException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first service config mapping in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching service config mapping, or <code>null</code> if a matching service config mapping could not be found
	*/
	public static ServiceConfigMapping fetchByUuid_C_First(String uuid,
		long companyId,
		OrderByComparator<ServiceConfigMapping> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last service config mapping in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching service config mapping
	* @throws NoSuchServiceConfigMappingException if a matching service config mapping could not be found
	*/
	public static ServiceConfigMapping findByUuid_C_Last(String uuid,
		long companyId,
		OrderByComparator<ServiceConfigMapping> orderByComparator)
		throws pay.gate.integration.dvc.exception.NoSuchServiceConfigMappingException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last service config mapping in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching service config mapping, or <code>null</code> if a matching service config mapping could not be found
	*/
	public static ServiceConfigMapping fetchByUuid_C_Last(String uuid,
		long companyId,
		OrderByComparator<ServiceConfigMapping> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the service config mappings before and after the current service config mapping in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param serviceConfigMappingId the primary key of the current service config mapping
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next service config mapping
	* @throws NoSuchServiceConfigMappingException if a service config mapping with the primary key could not be found
	*/
	public static ServiceConfigMapping[] findByUuid_C_PrevAndNext(
		long serviceConfigMappingId, String uuid, long companyId,
		OrderByComparator<ServiceConfigMapping> orderByComparator)
		throws pay.gate.integration.dvc.exception.NoSuchServiceConfigMappingException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(serviceConfigMappingId, uuid,
			companyId, orderByComparator);
	}

	/**
	* Removes all the service config mappings where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public static void removeByUuid_C(String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of service config mappings where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching service config mappings
	*/
	public static int countByUuid_C(String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Returns all the service config mappings where groupId = &#63; and maTTHC = &#63;.
	*
	* @param groupId the group ID
	* @param maTTHC the ma tthc
	* @return the matching service config mappings
	*/
	public static List<ServiceConfigMapping> findByG_MATTHC(long groupId,
		String maTTHC) {
		return getPersistence().findByG_MATTHC(groupId, maTTHC);
	}

	/**
	* Returns a range of all the service config mappings where groupId = &#63; and maTTHC = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceConfigMappingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param maTTHC the ma tthc
	* @param start the lower bound of the range of service config mappings
	* @param end the upper bound of the range of service config mappings (not inclusive)
	* @return the range of matching service config mappings
	*/
	public static List<ServiceConfigMapping> findByG_MATTHC(long groupId,
		String maTTHC, int start, int end) {
		return getPersistence().findByG_MATTHC(groupId, maTTHC, start, end);
	}

	/**
	* Returns an ordered range of all the service config mappings where groupId = &#63; and maTTHC = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceConfigMappingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param maTTHC the ma tthc
	* @param start the lower bound of the range of service config mappings
	* @param end the upper bound of the range of service config mappings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching service config mappings
	*/
	public static List<ServiceConfigMapping> findByG_MATTHC(long groupId,
		String maTTHC, int start, int end,
		OrderByComparator<ServiceConfigMapping> orderByComparator) {
		return getPersistence()
				   .findByG_MATTHC(groupId, maTTHC, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the service config mappings where groupId = &#63; and maTTHC = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceConfigMappingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param maTTHC the ma tthc
	* @param start the lower bound of the range of service config mappings
	* @param end the upper bound of the range of service config mappings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching service config mappings
	*/
	public static List<ServiceConfigMapping> findByG_MATTHC(long groupId,
		String maTTHC, int start, int end,
		OrderByComparator<ServiceConfigMapping> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByG_MATTHC(groupId, maTTHC, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first service config mapping in the ordered set where groupId = &#63; and maTTHC = &#63;.
	*
	* @param groupId the group ID
	* @param maTTHC the ma tthc
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching service config mapping
	* @throws NoSuchServiceConfigMappingException if a matching service config mapping could not be found
	*/
	public static ServiceConfigMapping findByG_MATTHC_First(long groupId,
		String maTTHC, OrderByComparator<ServiceConfigMapping> orderByComparator)
		throws pay.gate.integration.dvc.exception.NoSuchServiceConfigMappingException {
		return getPersistence()
				   .findByG_MATTHC_First(groupId, maTTHC, orderByComparator);
	}

	/**
	* Returns the first service config mapping in the ordered set where groupId = &#63; and maTTHC = &#63;.
	*
	* @param groupId the group ID
	* @param maTTHC the ma tthc
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching service config mapping, or <code>null</code> if a matching service config mapping could not be found
	*/
	public static ServiceConfigMapping fetchByG_MATTHC_First(long groupId,
		String maTTHC, OrderByComparator<ServiceConfigMapping> orderByComparator) {
		return getPersistence()
				   .fetchByG_MATTHC_First(groupId, maTTHC, orderByComparator);
	}

	/**
	* Returns the last service config mapping in the ordered set where groupId = &#63; and maTTHC = &#63;.
	*
	* @param groupId the group ID
	* @param maTTHC the ma tthc
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching service config mapping
	* @throws NoSuchServiceConfigMappingException if a matching service config mapping could not be found
	*/
	public static ServiceConfigMapping findByG_MATTHC_Last(long groupId,
		String maTTHC, OrderByComparator<ServiceConfigMapping> orderByComparator)
		throws pay.gate.integration.dvc.exception.NoSuchServiceConfigMappingException {
		return getPersistence()
				   .findByG_MATTHC_Last(groupId, maTTHC, orderByComparator);
	}

	/**
	* Returns the last service config mapping in the ordered set where groupId = &#63; and maTTHC = &#63;.
	*
	* @param groupId the group ID
	* @param maTTHC the ma tthc
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching service config mapping, or <code>null</code> if a matching service config mapping could not be found
	*/
	public static ServiceConfigMapping fetchByG_MATTHC_Last(long groupId,
		String maTTHC, OrderByComparator<ServiceConfigMapping> orderByComparator) {
		return getPersistence()
				   .fetchByG_MATTHC_Last(groupId, maTTHC, orderByComparator);
	}

	/**
	* Returns the service config mappings before and after the current service config mapping in the ordered set where groupId = &#63; and maTTHC = &#63;.
	*
	* @param serviceConfigMappingId the primary key of the current service config mapping
	* @param groupId the group ID
	* @param maTTHC the ma tthc
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next service config mapping
	* @throws NoSuchServiceConfigMappingException if a service config mapping with the primary key could not be found
	*/
	public static ServiceConfigMapping[] findByG_MATTHC_PrevAndNext(
		long serviceConfigMappingId, long groupId, String maTTHC,
		OrderByComparator<ServiceConfigMapping> orderByComparator)
		throws pay.gate.integration.dvc.exception.NoSuchServiceConfigMappingException {
		return getPersistence()
				   .findByG_MATTHC_PrevAndNext(serviceConfigMappingId, groupId,
			maTTHC, orderByComparator);
	}

	/**
	* Removes all the service config mappings where groupId = &#63; and maTTHC = &#63; from the database.
	*
	* @param groupId the group ID
	* @param maTTHC the ma tthc
	*/
	public static void removeByG_MATTHC(long groupId, String maTTHC) {
		getPersistence().removeByG_MATTHC(groupId, maTTHC);
	}

	/**
	* Returns the number of service config mappings where groupId = &#63; and maTTHC = &#63;.
	*
	* @param groupId the group ID
	* @param maTTHC the ma tthc
	* @return the number of matching service config mappings
	*/
	public static int countByG_MATTHC(long groupId, String maTTHC) {
		return getPersistence().countByG_MATTHC(groupId, maTTHC);
	}

	/**
	* Caches the service config mapping in the entity cache if it is enabled.
	*
	* @param serviceConfigMapping the service config mapping
	*/
	public static void cacheResult(ServiceConfigMapping serviceConfigMapping) {
		getPersistence().cacheResult(serviceConfigMapping);
	}

	/**
	* Caches the service config mappings in the entity cache if it is enabled.
	*
	* @param serviceConfigMappings the service config mappings
	*/
	public static void cacheResult(
		List<ServiceConfigMapping> serviceConfigMappings) {
		getPersistence().cacheResult(serviceConfigMappings);
	}

	/**
	* Creates a new service config mapping with the primary key. Does not add the service config mapping to the database.
	*
	* @param serviceConfigMappingId the primary key for the new service config mapping
	* @return the new service config mapping
	*/
	public static ServiceConfigMapping create(long serviceConfigMappingId) {
		return getPersistence().create(serviceConfigMappingId);
	}

	/**
	* Removes the service config mapping with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param serviceConfigMappingId the primary key of the service config mapping
	* @return the service config mapping that was removed
	* @throws NoSuchServiceConfigMappingException if a service config mapping with the primary key could not be found
	*/
	public static ServiceConfigMapping remove(long serviceConfigMappingId)
		throws pay.gate.integration.dvc.exception.NoSuchServiceConfigMappingException {
		return getPersistence().remove(serviceConfigMappingId);
	}

	public static ServiceConfigMapping updateImpl(
		ServiceConfigMapping serviceConfigMapping) {
		return getPersistence().updateImpl(serviceConfigMapping);
	}

	/**
	* Returns the service config mapping with the primary key or throws a {@link NoSuchServiceConfigMappingException} if it could not be found.
	*
	* @param serviceConfigMappingId the primary key of the service config mapping
	* @return the service config mapping
	* @throws NoSuchServiceConfigMappingException if a service config mapping with the primary key could not be found
	*/
	public static ServiceConfigMapping findByPrimaryKey(
		long serviceConfigMappingId)
		throws pay.gate.integration.dvc.exception.NoSuchServiceConfigMappingException {
		return getPersistence().findByPrimaryKey(serviceConfigMappingId);
	}

	/**
	* Returns the service config mapping with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param serviceConfigMappingId the primary key of the service config mapping
	* @return the service config mapping, or <code>null</code> if a service config mapping with the primary key could not be found
	*/
	public static ServiceConfigMapping fetchByPrimaryKey(
		long serviceConfigMappingId) {
		return getPersistence().fetchByPrimaryKey(serviceConfigMappingId);
	}

	public static java.util.Map<java.io.Serializable, ServiceConfigMapping> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the service config mappings.
	*
	* @return the service config mappings
	*/
	public static List<ServiceConfigMapping> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the service config mappings.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceConfigMappingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of service config mappings
	* @param end the upper bound of the range of service config mappings (not inclusive)
	* @return the range of service config mappings
	*/
	public static List<ServiceConfigMapping> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the service config mappings.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceConfigMappingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of service config mappings
	* @param end the upper bound of the range of service config mappings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of service config mappings
	*/
	public static List<ServiceConfigMapping> findAll(int start, int end,
		OrderByComparator<ServiceConfigMapping> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the service config mappings.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceConfigMappingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of service config mappings
	* @param end the upper bound of the range of service config mappings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of service config mappings
	*/
	public static List<ServiceConfigMapping> findAll(int start, int end,
		OrderByComparator<ServiceConfigMapping> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the service config mappings from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of service config mappings.
	*
	* @return the number of service config mappings
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static ServiceConfigMappingPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<ServiceConfigMappingPersistence, ServiceConfigMappingPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(ServiceConfigMappingPersistence.class);

		ServiceTracker<ServiceConfigMappingPersistence, ServiceConfigMappingPersistence> serviceTracker =
			new ServiceTracker<ServiceConfigMappingPersistence, ServiceConfigMappingPersistence>(bundle.getBundleContext(),
				ServiceConfigMappingPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}