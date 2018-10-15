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

import org.opencps.dossiermgt.model.PaymentConfig;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the payment config service. This utility wraps {@link org.opencps.dossiermgt.service.persistence.impl.PaymentConfigPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author huymq
 * @see PaymentConfigPersistence
 * @see org.opencps.dossiermgt.service.persistence.impl.PaymentConfigPersistenceImpl
 * @generated
 */
@ProviderType
public class PaymentConfigUtil {
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
	public static void clearCache(PaymentConfig paymentConfig) {
		getPersistence().clearCache(paymentConfig);
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
	public static List<PaymentConfig> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<PaymentConfig> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<PaymentConfig> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<PaymentConfig> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static PaymentConfig update(PaymentConfig paymentConfig) {
		return getPersistence().update(paymentConfig);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static PaymentConfig update(PaymentConfig paymentConfig,
		ServiceContext serviceContext) {
		return getPersistence().update(paymentConfig, serviceContext);
	}

	/**
	* Returns all the payment configs where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching payment configs
	*/
	public static List<PaymentConfig> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the payment configs where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PaymentConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of payment configs
	* @param end the upper bound of the range of payment configs (not inclusive)
	* @return the range of matching payment configs
	*/
	public static List<PaymentConfig> findByUuid(String uuid, int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the payment configs where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PaymentConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of payment configs
	* @param end the upper bound of the range of payment configs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching payment configs
	*/
	public static List<PaymentConfig> findByUuid(String uuid, int start,
		int end, OrderByComparator<PaymentConfig> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the payment configs where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PaymentConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of payment configs
	* @param end the upper bound of the range of payment configs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching payment configs
	*/
	public static List<PaymentConfig> findByUuid(String uuid, int start,
		int end, OrderByComparator<PaymentConfig> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first payment config in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching payment config
	* @throws NoSuchPaymentConfigException if a matching payment config could not be found
	*/
	public static PaymentConfig findByUuid_First(String uuid,
		OrderByComparator<PaymentConfig> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchPaymentConfigException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first payment config in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching payment config, or <code>null</code> if a matching payment config could not be found
	*/
	public static PaymentConfig fetchByUuid_First(String uuid,
		OrderByComparator<PaymentConfig> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last payment config in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching payment config
	* @throws NoSuchPaymentConfigException if a matching payment config could not be found
	*/
	public static PaymentConfig findByUuid_Last(String uuid,
		OrderByComparator<PaymentConfig> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchPaymentConfigException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last payment config in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching payment config, or <code>null</code> if a matching payment config could not be found
	*/
	public static PaymentConfig fetchByUuid_Last(String uuid,
		OrderByComparator<PaymentConfig> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the payment configs before and after the current payment config in the ordered set where uuid = &#63;.
	*
	* @param paymentConfigId the primary key of the current payment config
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next payment config
	* @throws NoSuchPaymentConfigException if a payment config with the primary key could not be found
	*/
	public static PaymentConfig[] findByUuid_PrevAndNext(long paymentConfigId,
		String uuid, OrderByComparator<PaymentConfig> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchPaymentConfigException {
		return getPersistence()
				   .findByUuid_PrevAndNext(paymentConfigId, uuid,
			orderByComparator);
	}

	/**
	* Removes all the payment configs where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of payment configs where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching payment configs
	*/
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the payment config where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchPaymentConfigException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching payment config
	* @throws NoSuchPaymentConfigException if a matching payment config could not be found
	*/
	public static PaymentConfig findByUUID_G(String uuid, long groupId)
		throws org.opencps.dossiermgt.exception.NoSuchPaymentConfigException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the payment config where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching payment config, or <code>null</code> if a matching payment config could not be found
	*/
	public static PaymentConfig fetchByUUID_G(String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the payment config where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching payment config, or <code>null</code> if a matching payment config could not be found
	*/
	public static PaymentConfig fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the payment config where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the payment config that was removed
	*/
	public static PaymentConfig removeByUUID_G(String uuid, long groupId)
		throws org.opencps.dossiermgt.exception.NoSuchPaymentConfigException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of payment configs where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching payment configs
	*/
	public static int countByUUID_G(String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the payment configs where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching payment configs
	*/
	public static List<PaymentConfig> findByUuid_C(String uuid, long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	* Returns a range of all the payment configs where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PaymentConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of payment configs
	* @param end the upper bound of the range of payment configs (not inclusive)
	* @return the range of matching payment configs
	*/
	public static List<PaymentConfig> findByUuid_C(String uuid, long companyId,
		int start, int end) {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	* Returns an ordered range of all the payment configs where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PaymentConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of payment configs
	* @param end the upper bound of the range of payment configs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching payment configs
	*/
	public static List<PaymentConfig> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<PaymentConfig> orderByComparator) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the payment configs where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PaymentConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of payment configs
	* @param end the upper bound of the range of payment configs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching payment configs
	*/
	public static List<PaymentConfig> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<PaymentConfig> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first payment config in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching payment config
	* @throws NoSuchPaymentConfigException if a matching payment config could not be found
	*/
	public static PaymentConfig findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<PaymentConfig> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchPaymentConfigException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first payment config in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching payment config, or <code>null</code> if a matching payment config could not be found
	*/
	public static PaymentConfig fetchByUuid_C_First(String uuid,
		long companyId, OrderByComparator<PaymentConfig> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last payment config in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching payment config
	* @throws NoSuchPaymentConfigException if a matching payment config could not be found
	*/
	public static PaymentConfig findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<PaymentConfig> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchPaymentConfigException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last payment config in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching payment config, or <code>null</code> if a matching payment config could not be found
	*/
	public static PaymentConfig fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<PaymentConfig> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the payment configs before and after the current payment config in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param paymentConfigId the primary key of the current payment config
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next payment config
	* @throws NoSuchPaymentConfigException if a payment config with the primary key could not be found
	*/
	public static PaymentConfig[] findByUuid_C_PrevAndNext(
		long paymentConfigId, String uuid, long companyId,
		OrderByComparator<PaymentConfig> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchPaymentConfigException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(paymentConfigId, uuid, companyId,
			orderByComparator);
	}

	/**
	* Removes all the payment configs where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public static void removeByUuid_C(String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of payment configs where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching payment configs
	*/
	public static int countByUuid_C(String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Returns all the payment configs where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching payment configs
	*/
	public static List<PaymentConfig> findByFB_GID(long groupId) {
		return getPersistence().findByFB_GID(groupId);
	}

	/**
	* Returns a range of all the payment configs where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PaymentConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of payment configs
	* @param end the upper bound of the range of payment configs (not inclusive)
	* @return the range of matching payment configs
	*/
	public static List<PaymentConfig> findByFB_GID(long groupId, int start,
		int end) {
		return getPersistence().findByFB_GID(groupId, start, end);
	}

	/**
	* Returns an ordered range of all the payment configs where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PaymentConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of payment configs
	* @param end the upper bound of the range of payment configs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching payment configs
	*/
	public static List<PaymentConfig> findByFB_GID(long groupId, int start,
		int end, OrderByComparator<PaymentConfig> orderByComparator) {
		return getPersistence()
				   .findByFB_GID(groupId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the payment configs where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PaymentConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of payment configs
	* @param end the upper bound of the range of payment configs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching payment configs
	*/
	public static List<PaymentConfig> findByFB_GID(long groupId, int start,
		int end, OrderByComparator<PaymentConfig> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByFB_GID(groupId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first payment config in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching payment config
	* @throws NoSuchPaymentConfigException if a matching payment config could not be found
	*/
	public static PaymentConfig findByFB_GID_First(long groupId,
		OrderByComparator<PaymentConfig> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchPaymentConfigException {
		return getPersistence().findByFB_GID_First(groupId, orderByComparator);
	}

	/**
	* Returns the first payment config in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching payment config, or <code>null</code> if a matching payment config could not be found
	*/
	public static PaymentConfig fetchByFB_GID_First(long groupId,
		OrderByComparator<PaymentConfig> orderByComparator) {
		return getPersistence().fetchByFB_GID_First(groupId, orderByComparator);
	}

	/**
	* Returns the last payment config in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching payment config
	* @throws NoSuchPaymentConfigException if a matching payment config could not be found
	*/
	public static PaymentConfig findByFB_GID_Last(long groupId,
		OrderByComparator<PaymentConfig> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchPaymentConfigException {
		return getPersistence().findByFB_GID_Last(groupId, orderByComparator);
	}

	/**
	* Returns the last payment config in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching payment config, or <code>null</code> if a matching payment config could not be found
	*/
	public static PaymentConfig fetchByFB_GID_Last(long groupId,
		OrderByComparator<PaymentConfig> orderByComparator) {
		return getPersistence().fetchByFB_GID_Last(groupId, orderByComparator);
	}

	/**
	* Returns the payment configs before and after the current payment config in the ordered set where groupId = &#63;.
	*
	* @param paymentConfigId the primary key of the current payment config
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next payment config
	* @throws NoSuchPaymentConfigException if a payment config with the primary key could not be found
	*/
	public static PaymentConfig[] findByFB_GID_PrevAndNext(
		long paymentConfigId, long groupId,
		OrderByComparator<PaymentConfig> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchPaymentConfigException {
		return getPersistence()
				   .findByFB_GID_PrevAndNext(paymentConfigId, groupId,
			orderByComparator);
	}

	/**
	* Removes all the payment configs where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public static void removeByFB_GID(long groupId) {
		getPersistence().removeByFB_GID(groupId);
	}

	/**
	* Returns the number of payment configs where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching payment configs
	*/
	public static int countByFB_GID(long groupId) {
		return getPersistence().countByFB_GID(groupId);
	}

	/**
	* Returns the payment config where groupId = &#63; and govAgencyCode = &#63; or throws a {@link NoSuchPaymentConfigException} if it could not be found.
	*
	* @param groupId the group ID
	* @param govAgencyCode the gov agency code
	* @return the matching payment config
	* @throws NoSuchPaymentConfigException if a matching payment config could not be found
	*/
	public static PaymentConfig findByFB_GID_govAgencyCode(long groupId,
		String govAgencyCode)
		throws org.opencps.dossiermgt.exception.NoSuchPaymentConfigException {
		return getPersistence()
				   .findByFB_GID_govAgencyCode(groupId, govAgencyCode);
	}

	/**
	* Returns the payment config where groupId = &#63; and govAgencyCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param govAgencyCode the gov agency code
	* @return the matching payment config, or <code>null</code> if a matching payment config could not be found
	*/
	public static PaymentConfig fetchByFB_GID_govAgencyCode(long groupId,
		String govAgencyCode) {
		return getPersistence()
				   .fetchByFB_GID_govAgencyCode(groupId, govAgencyCode);
	}

	/**
	* Returns the payment config where groupId = &#63; and govAgencyCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param govAgencyCode the gov agency code
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching payment config, or <code>null</code> if a matching payment config could not be found
	*/
	public static PaymentConfig fetchByFB_GID_govAgencyCode(long groupId,
		String govAgencyCode, boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByFB_GID_govAgencyCode(groupId, govAgencyCode,
			retrieveFromCache);
	}

	/**
	* Removes the payment config where groupId = &#63; and govAgencyCode = &#63; from the database.
	*
	* @param groupId the group ID
	* @param govAgencyCode the gov agency code
	* @return the payment config that was removed
	*/
	public static PaymentConfig removeByFB_GID_govAgencyCode(long groupId,
		String govAgencyCode)
		throws org.opencps.dossiermgt.exception.NoSuchPaymentConfigException {
		return getPersistence()
				   .removeByFB_GID_govAgencyCode(groupId, govAgencyCode);
	}

	/**
	* Returns the number of payment configs where groupId = &#63; and govAgencyCode = &#63;.
	*
	* @param groupId the group ID
	* @param govAgencyCode the gov agency code
	* @return the number of matching payment configs
	*/
	public static int countByFB_GID_govAgencyCode(long groupId,
		String govAgencyCode) {
		return getPersistence()
				   .countByFB_GID_govAgencyCode(groupId, govAgencyCode);
	}

	/**
	* Caches the payment config in the entity cache if it is enabled.
	*
	* @param paymentConfig the payment config
	*/
	public static void cacheResult(PaymentConfig paymentConfig) {
		getPersistence().cacheResult(paymentConfig);
	}

	/**
	* Caches the payment configs in the entity cache if it is enabled.
	*
	* @param paymentConfigs the payment configs
	*/
	public static void cacheResult(List<PaymentConfig> paymentConfigs) {
		getPersistence().cacheResult(paymentConfigs);
	}

	/**
	* Creates a new payment config with the primary key. Does not add the payment config to the database.
	*
	* @param paymentConfigId the primary key for the new payment config
	* @return the new payment config
	*/
	public static PaymentConfig create(long paymentConfigId) {
		return getPersistence().create(paymentConfigId);
	}

	/**
	* Removes the payment config with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param paymentConfigId the primary key of the payment config
	* @return the payment config that was removed
	* @throws NoSuchPaymentConfigException if a payment config with the primary key could not be found
	*/
	public static PaymentConfig remove(long paymentConfigId)
		throws org.opencps.dossiermgt.exception.NoSuchPaymentConfigException {
		return getPersistence().remove(paymentConfigId);
	}

	public static PaymentConfig updateImpl(PaymentConfig paymentConfig) {
		return getPersistence().updateImpl(paymentConfig);
	}

	/**
	* Returns the payment config with the primary key or throws a {@link NoSuchPaymentConfigException} if it could not be found.
	*
	* @param paymentConfigId the primary key of the payment config
	* @return the payment config
	* @throws NoSuchPaymentConfigException if a payment config with the primary key could not be found
	*/
	public static PaymentConfig findByPrimaryKey(long paymentConfigId)
		throws org.opencps.dossiermgt.exception.NoSuchPaymentConfigException {
		return getPersistence().findByPrimaryKey(paymentConfigId);
	}

	/**
	* Returns the payment config with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param paymentConfigId the primary key of the payment config
	* @return the payment config, or <code>null</code> if a payment config with the primary key could not be found
	*/
	public static PaymentConfig fetchByPrimaryKey(long paymentConfigId) {
		return getPersistence().fetchByPrimaryKey(paymentConfigId);
	}

	public static java.util.Map<java.io.Serializable, PaymentConfig> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the payment configs.
	*
	* @return the payment configs
	*/
	public static List<PaymentConfig> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the payment configs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PaymentConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of payment configs
	* @param end the upper bound of the range of payment configs (not inclusive)
	* @return the range of payment configs
	*/
	public static List<PaymentConfig> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the payment configs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PaymentConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of payment configs
	* @param end the upper bound of the range of payment configs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of payment configs
	*/
	public static List<PaymentConfig> findAll(int start, int end,
		OrderByComparator<PaymentConfig> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the payment configs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PaymentConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of payment configs
	* @param end the upper bound of the range of payment configs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of payment configs
	*/
	public static List<PaymentConfig> findAll(int start, int end,
		OrderByComparator<PaymentConfig> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the payment configs from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of payment configs.
	*
	* @return the number of payment configs
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static PaymentConfigPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<PaymentConfigPersistence, PaymentConfigPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(PaymentConfigPersistence.class);

		ServiceTracker<PaymentConfigPersistence, PaymentConfigPersistence> serviceTracker =
			new ServiceTracker<PaymentConfigPersistence, PaymentConfigPersistence>(bundle.getBundleContext(),
				PaymentConfigPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}