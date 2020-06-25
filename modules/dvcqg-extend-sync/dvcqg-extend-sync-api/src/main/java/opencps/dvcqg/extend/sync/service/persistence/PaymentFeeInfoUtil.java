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

package opencps.dvcqg.extend.sync.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import opencps.dvcqg.extend.sync.model.PaymentFeeInfo;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the payment fee info service. This utility wraps {@link opencps.dvcqg.extend.sync.service.persistence.impl.PaymentFeeInfoPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PaymentFeeInfoPersistence
 * @see opencps.dvcqg.extend.sync.service.persistence.impl.PaymentFeeInfoPersistenceImpl
 * @generated
 */
@ProviderType
public class PaymentFeeInfoUtil {
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
	public static void clearCache(PaymentFeeInfo paymentFeeInfo) {
		getPersistence().clearCache(paymentFeeInfo);
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
	public static List<PaymentFeeInfo> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<PaymentFeeInfo> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<PaymentFeeInfo> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<PaymentFeeInfo> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static PaymentFeeInfo update(PaymentFeeInfo paymentFeeInfo) {
		return getPersistence().update(paymentFeeInfo);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static PaymentFeeInfo update(PaymentFeeInfo paymentFeeInfo,
		ServiceContext serviceContext) {
		return getPersistence().update(paymentFeeInfo, serviceContext);
	}

	/**
	* Returns all the payment fee infos where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching payment fee infos
	*/
	public static List<PaymentFeeInfo> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the payment fee infos where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PaymentFeeInfoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of payment fee infos
	* @param end the upper bound of the range of payment fee infos (not inclusive)
	* @return the range of matching payment fee infos
	*/
	public static List<PaymentFeeInfo> findByUuid(String uuid, int start,
		int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the payment fee infos where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PaymentFeeInfoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of payment fee infos
	* @param end the upper bound of the range of payment fee infos (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching payment fee infos
	*/
	public static List<PaymentFeeInfo> findByUuid(String uuid, int start,
		int end, OrderByComparator<PaymentFeeInfo> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the payment fee infos where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PaymentFeeInfoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of payment fee infos
	* @param end the upper bound of the range of payment fee infos (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching payment fee infos
	*/
	public static List<PaymentFeeInfo> findByUuid(String uuid, int start,
		int end, OrderByComparator<PaymentFeeInfo> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first payment fee info in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching payment fee info
	* @throws NoSuchPaymentFeeInfoException if a matching payment fee info could not be found
	*/
	public static PaymentFeeInfo findByUuid_First(String uuid,
		OrderByComparator<PaymentFeeInfo> orderByComparator)
		throws opencps.dvcqg.extend.sync.exception.NoSuchPaymentFeeInfoException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first payment fee info in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching payment fee info, or <code>null</code> if a matching payment fee info could not be found
	*/
	public static PaymentFeeInfo fetchByUuid_First(String uuid,
		OrderByComparator<PaymentFeeInfo> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last payment fee info in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching payment fee info
	* @throws NoSuchPaymentFeeInfoException if a matching payment fee info could not be found
	*/
	public static PaymentFeeInfo findByUuid_Last(String uuid,
		OrderByComparator<PaymentFeeInfo> orderByComparator)
		throws opencps.dvcqg.extend.sync.exception.NoSuchPaymentFeeInfoException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last payment fee info in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching payment fee info, or <code>null</code> if a matching payment fee info could not be found
	*/
	public static PaymentFeeInfo fetchByUuid_Last(String uuid,
		OrderByComparator<PaymentFeeInfo> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the payment fee infos before and after the current payment fee info in the ordered set where uuid = &#63;.
	*
	* @param paymentFeeInfoId the primary key of the current payment fee info
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next payment fee info
	* @throws NoSuchPaymentFeeInfoException if a payment fee info with the primary key could not be found
	*/
	public static PaymentFeeInfo[] findByUuid_PrevAndNext(
		long paymentFeeInfoId, String uuid,
		OrderByComparator<PaymentFeeInfo> orderByComparator)
		throws opencps.dvcqg.extend.sync.exception.NoSuchPaymentFeeInfoException {
		return getPersistence()
				   .findByUuid_PrevAndNext(paymentFeeInfoId, uuid,
			orderByComparator);
	}

	/**
	* Removes all the payment fee infos where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of payment fee infos where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching payment fee infos
	*/
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the payment fee info where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchPaymentFeeInfoException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching payment fee info
	* @throws NoSuchPaymentFeeInfoException if a matching payment fee info could not be found
	*/
	public static PaymentFeeInfo findByUUID_G(String uuid, long groupId)
		throws opencps.dvcqg.extend.sync.exception.NoSuchPaymentFeeInfoException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the payment fee info where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching payment fee info, or <code>null</code> if a matching payment fee info could not be found
	*/
	public static PaymentFeeInfo fetchByUUID_G(String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the payment fee info where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching payment fee info, or <code>null</code> if a matching payment fee info could not be found
	*/
	public static PaymentFeeInfo fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the payment fee info where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the payment fee info that was removed
	*/
	public static PaymentFeeInfo removeByUUID_G(String uuid, long groupId)
		throws opencps.dvcqg.extend.sync.exception.NoSuchPaymentFeeInfoException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of payment fee infos where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching payment fee infos
	*/
	public static int countByUUID_G(String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the payment fee infos where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching payment fee infos
	*/
	public static List<PaymentFeeInfo> findByUuid_C(String uuid, long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	* Returns a range of all the payment fee infos where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PaymentFeeInfoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of payment fee infos
	* @param end the upper bound of the range of payment fee infos (not inclusive)
	* @return the range of matching payment fee infos
	*/
	public static List<PaymentFeeInfo> findByUuid_C(String uuid,
		long companyId, int start, int end) {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	* Returns an ordered range of all the payment fee infos where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PaymentFeeInfoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of payment fee infos
	* @param end the upper bound of the range of payment fee infos (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching payment fee infos
	*/
	public static List<PaymentFeeInfo> findByUuid_C(String uuid,
		long companyId, int start, int end,
		OrderByComparator<PaymentFeeInfo> orderByComparator) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the payment fee infos where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PaymentFeeInfoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of payment fee infos
	* @param end the upper bound of the range of payment fee infos (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching payment fee infos
	*/
	public static List<PaymentFeeInfo> findByUuid_C(String uuid,
		long companyId, int start, int end,
		OrderByComparator<PaymentFeeInfo> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first payment fee info in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching payment fee info
	* @throws NoSuchPaymentFeeInfoException if a matching payment fee info could not be found
	*/
	public static PaymentFeeInfo findByUuid_C_First(String uuid,
		long companyId, OrderByComparator<PaymentFeeInfo> orderByComparator)
		throws opencps.dvcqg.extend.sync.exception.NoSuchPaymentFeeInfoException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first payment fee info in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching payment fee info, or <code>null</code> if a matching payment fee info could not be found
	*/
	public static PaymentFeeInfo fetchByUuid_C_First(String uuid,
		long companyId, OrderByComparator<PaymentFeeInfo> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last payment fee info in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching payment fee info
	* @throws NoSuchPaymentFeeInfoException if a matching payment fee info could not be found
	*/
	public static PaymentFeeInfo findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<PaymentFeeInfo> orderByComparator)
		throws opencps.dvcqg.extend.sync.exception.NoSuchPaymentFeeInfoException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last payment fee info in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching payment fee info, or <code>null</code> if a matching payment fee info could not be found
	*/
	public static PaymentFeeInfo fetchByUuid_C_Last(String uuid,
		long companyId, OrderByComparator<PaymentFeeInfo> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the payment fee infos before and after the current payment fee info in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param paymentFeeInfoId the primary key of the current payment fee info
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next payment fee info
	* @throws NoSuchPaymentFeeInfoException if a payment fee info with the primary key could not be found
	*/
	public static PaymentFeeInfo[] findByUuid_C_PrevAndNext(
		long paymentFeeInfoId, String uuid, long companyId,
		OrderByComparator<PaymentFeeInfo> orderByComparator)
		throws opencps.dvcqg.extend.sync.exception.NoSuchPaymentFeeInfoException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(paymentFeeInfoId, uuid, companyId,
			orderByComparator);
	}

	/**
	* Removes all the payment fee infos where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public static void removeByUuid_C(String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of payment fee infos where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching payment fee infos
	*/
	public static int countByUuid_C(String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Returns all the payment fee infos where serviceConfigMappingId = &#63;.
	*
	* @param serviceConfigMappingId the service config mapping ID
	* @return the matching payment fee infos
	*/
	public static List<PaymentFeeInfo> findByServiceConfigMappingId(
		long serviceConfigMappingId) {
		return getPersistence()
				   .findByServiceConfigMappingId(serviceConfigMappingId);
	}

	/**
	* Returns a range of all the payment fee infos where serviceConfigMappingId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PaymentFeeInfoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param serviceConfigMappingId the service config mapping ID
	* @param start the lower bound of the range of payment fee infos
	* @param end the upper bound of the range of payment fee infos (not inclusive)
	* @return the range of matching payment fee infos
	*/
	public static List<PaymentFeeInfo> findByServiceConfigMappingId(
		long serviceConfigMappingId, int start, int end) {
		return getPersistence()
				   .findByServiceConfigMappingId(serviceConfigMappingId, start,
			end);
	}

	/**
	* Returns an ordered range of all the payment fee infos where serviceConfigMappingId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PaymentFeeInfoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param serviceConfigMappingId the service config mapping ID
	* @param start the lower bound of the range of payment fee infos
	* @param end the upper bound of the range of payment fee infos (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching payment fee infos
	*/
	public static List<PaymentFeeInfo> findByServiceConfigMappingId(
		long serviceConfigMappingId, int start, int end,
		OrderByComparator<PaymentFeeInfo> orderByComparator) {
		return getPersistence()
				   .findByServiceConfigMappingId(serviceConfigMappingId, start,
			end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the payment fee infos where serviceConfigMappingId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PaymentFeeInfoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param serviceConfigMappingId the service config mapping ID
	* @param start the lower bound of the range of payment fee infos
	* @param end the upper bound of the range of payment fee infos (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching payment fee infos
	*/
	public static List<PaymentFeeInfo> findByServiceConfigMappingId(
		long serviceConfigMappingId, int start, int end,
		OrderByComparator<PaymentFeeInfo> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByServiceConfigMappingId(serviceConfigMappingId, start,
			end, orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first payment fee info in the ordered set where serviceConfigMappingId = &#63;.
	*
	* @param serviceConfigMappingId the service config mapping ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching payment fee info
	* @throws NoSuchPaymentFeeInfoException if a matching payment fee info could not be found
	*/
	public static PaymentFeeInfo findByServiceConfigMappingId_First(
		long serviceConfigMappingId,
		OrderByComparator<PaymentFeeInfo> orderByComparator)
		throws opencps.dvcqg.extend.sync.exception.NoSuchPaymentFeeInfoException {
		return getPersistence()
				   .findByServiceConfigMappingId_First(serviceConfigMappingId,
			orderByComparator);
	}

	/**
	* Returns the first payment fee info in the ordered set where serviceConfigMappingId = &#63;.
	*
	* @param serviceConfigMappingId the service config mapping ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching payment fee info, or <code>null</code> if a matching payment fee info could not be found
	*/
	public static PaymentFeeInfo fetchByServiceConfigMappingId_First(
		long serviceConfigMappingId,
		OrderByComparator<PaymentFeeInfo> orderByComparator) {
		return getPersistence()
				   .fetchByServiceConfigMappingId_First(serviceConfigMappingId,
			orderByComparator);
	}

	/**
	* Returns the last payment fee info in the ordered set where serviceConfigMappingId = &#63;.
	*
	* @param serviceConfigMappingId the service config mapping ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching payment fee info
	* @throws NoSuchPaymentFeeInfoException if a matching payment fee info could not be found
	*/
	public static PaymentFeeInfo findByServiceConfigMappingId_Last(
		long serviceConfigMappingId,
		OrderByComparator<PaymentFeeInfo> orderByComparator)
		throws opencps.dvcqg.extend.sync.exception.NoSuchPaymentFeeInfoException {
		return getPersistence()
				   .findByServiceConfigMappingId_Last(serviceConfigMappingId,
			orderByComparator);
	}

	/**
	* Returns the last payment fee info in the ordered set where serviceConfigMappingId = &#63;.
	*
	* @param serviceConfigMappingId the service config mapping ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching payment fee info, or <code>null</code> if a matching payment fee info could not be found
	*/
	public static PaymentFeeInfo fetchByServiceConfigMappingId_Last(
		long serviceConfigMappingId,
		OrderByComparator<PaymentFeeInfo> orderByComparator) {
		return getPersistence()
				   .fetchByServiceConfigMappingId_Last(serviceConfigMappingId,
			orderByComparator);
	}

	/**
	* Returns the payment fee infos before and after the current payment fee info in the ordered set where serviceConfigMappingId = &#63;.
	*
	* @param paymentFeeInfoId the primary key of the current payment fee info
	* @param serviceConfigMappingId the service config mapping ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next payment fee info
	* @throws NoSuchPaymentFeeInfoException if a payment fee info with the primary key could not be found
	*/
	public static PaymentFeeInfo[] findByServiceConfigMappingId_PrevAndNext(
		long paymentFeeInfoId, long serviceConfigMappingId,
		OrderByComparator<PaymentFeeInfo> orderByComparator)
		throws opencps.dvcqg.extend.sync.exception.NoSuchPaymentFeeInfoException {
		return getPersistence()
				   .findByServiceConfigMappingId_PrevAndNext(paymentFeeInfoId,
			serviceConfigMappingId, orderByComparator);
	}

	/**
	* Removes all the payment fee infos where serviceConfigMappingId = &#63; from the database.
	*
	* @param serviceConfigMappingId the service config mapping ID
	*/
	public static void removeByServiceConfigMappingId(
		long serviceConfigMappingId) {
		getPersistence().removeByServiceConfigMappingId(serviceConfigMappingId);
	}

	/**
	* Returns the number of payment fee infos where serviceConfigMappingId = &#63;.
	*
	* @param serviceConfigMappingId the service config mapping ID
	* @return the number of matching payment fee infos
	*/
	public static int countByServiceConfigMappingId(long serviceConfigMappingId) {
		return getPersistence()
				   .countByServiceConfigMappingId(serviceConfigMappingId);
	}

	/**
	* Caches the payment fee info in the entity cache if it is enabled.
	*
	* @param paymentFeeInfo the payment fee info
	*/
	public static void cacheResult(PaymentFeeInfo paymentFeeInfo) {
		getPersistence().cacheResult(paymentFeeInfo);
	}

	/**
	* Caches the payment fee infos in the entity cache if it is enabled.
	*
	* @param paymentFeeInfos the payment fee infos
	*/
	public static void cacheResult(List<PaymentFeeInfo> paymentFeeInfos) {
		getPersistence().cacheResult(paymentFeeInfos);
	}

	/**
	* Creates a new payment fee info with the primary key. Does not add the payment fee info to the database.
	*
	* @param paymentFeeInfoId the primary key for the new payment fee info
	* @return the new payment fee info
	*/
	public static PaymentFeeInfo create(long paymentFeeInfoId) {
		return getPersistence().create(paymentFeeInfoId);
	}

	/**
	* Removes the payment fee info with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param paymentFeeInfoId the primary key of the payment fee info
	* @return the payment fee info that was removed
	* @throws NoSuchPaymentFeeInfoException if a payment fee info with the primary key could not be found
	*/
	public static PaymentFeeInfo remove(long paymentFeeInfoId)
		throws opencps.dvcqg.extend.sync.exception.NoSuchPaymentFeeInfoException {
		return getPersistence().remove(paymentFeeInfoId);
	}

	public static PaymentFeeInfo updateImpl(PaymentFeeInfo paymentFeeInfo) {
		return getPersistence().updateImpl(paymentFeeInfo);
	}

	/**
	* Returns the payment fee info with the primary key or throws a {@link NoSuchPaymentFeeInfoException} if it could not be found.
	*
	* @param paymentFeeInfoId the primary key of the payment fee info
	* @return the payment fee info
	* @throws NoSuchPaymentFeeInfoException if a payment fee info with the primary key could not be found
	*/
	public static PaymentFeeInfo findByPrimaryKey(long paymentFeeInfoId)
		throws opencps.dvcqg.extend.sync.exception.NoSuchPaymentFeeInfoException {
		return getPersistence().findByPrimaryKey(paymentFeeInfoId);
	}

	/**
	* Returns the payment fee info with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param paymentFeeInfoId the primary key of the payment fee info
	* @return the payment fee info, or <code>null</code> if a payment fee info with the primary key could not be found
	*/
	public static PaymentFeeInfo fetchByPrimaryKey(long paymentFeeInfoId) {
		return getPersistence().fetchByPrimaryKey(paymentFeeInfoId);
	}

	public static java.util.Map<java.io.Serializable, PaymentFeeInfo> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the payment fee infos.
	*
	* @return the payment fee infos
	*/
	public static List<PaymentFeeInfo> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the payment fee infos.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PaymentFeeInfoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of payment fee infos
	* @param end the upper bound of the range of payment fee infos (not inclusive)
	* @return the range of payment fee infos
	*/
	public static List<PaymentFeeInfo> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the payment fee infos.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PaymentFeeInfoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of payment fee infos
	* @param end the upper bound of the range of payment fee infos (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of payment fee infos
	*/
	public static List<PaymentFeeInfo> findAll(int start, int end,
		OrderByComparator<PaymentFeeInfo> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the payment fee infos.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PaymentFeeInfoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of payment fee infos
	* @param end the upper bound of the range of payment fee infos (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of payment fee infos
	*/
	public static List<PaymentFeeInfo> findAll(int start, int end,
		OrderByComparator<PaymentFeeInfo> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the payment fee infos from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of payment fee infos.
	*
	* @return the number of payment fee infos
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static PaymentFeeInfoPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<PaymentFeeInfoPersistence, PaymentFeeInfoPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(PaymentFeeInfoPersistence.class);

		ServiceTracker<PaymentFeeInfoPersistence, PaymentFeeInfoPersistence> serviceTracker =
			new ServiceTracker<PaymentFeeInfoPersistence, PaymentFeeInfoPersistence>(bundle.getBundleContext(),
				PaymentFeeInfoPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}