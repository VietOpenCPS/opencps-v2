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

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import opencps.dvcqg.extend.sync.exception.NoSuchPaymentFeeInfoException;
import opencps.dvcqg.extend.sync.model.PaymentFeeInfo;

/**
 * The persistence interface for the payment fee info service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see opencps.dvcqg.extend.sync.service.persistence.impl.PaymentFeeInfoPersistenceImpl
 * @see PaymentFeeInfoUtil
 * @generated
 */
@ProviderType
public interface PaymentFeeInfoPersistence extends BasePersistence<PaymentFeeInfo> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link PaymentFeeInfoUtil} to access the payment fee info persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the payment fee infos where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching payment fee infos
	*/
	public java.util.List<PaymentFeeInfo> findByUuid(String uuid);

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
	public java.util.List<PaymentFeeInfo> findByUuid(String uuid, int start,
		int end);

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
	public java.util.List<PaymentFeeInfo> findByUuid(String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<PaymentFeeInfo> orderByComparator);

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
	public java.util.List<PaymentFeeInfo> findByUuid(String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<PaymentFeeInfo> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first payment fee info in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching payment fee info
	* @throws NoSuchPaymentFeeInfoException if a matching payment fee info could not be found
	*/
	public PaymentFeeInfo findByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<PaymentFeeInfo> orderByComparator)
		throws NoSuchPaymentFeeInfoException;

	/**
	* Returns the first payment fee info in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching payment fee info, or <code>null</code> if a matching payment fee info could not be found
	*/
	public PaymentFeeInfo fetchByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<PaymentFeeInfo> orderByComparator);

	/**
	* Returns the last payment fee info in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching payment fee info
	* @throws NoSuchPaymentFeeInfoException if a matching payment fee info could not be found
	*/
	public PaymentFeeInfo findByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<PaymentFeeInfo> orderByComparator)
		throws NoSuchPaymentFeeInfoException;

	/**
	* Returns the last payment fee info in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching payment fee info, or <code>null</code> if a matching payment fee info could not be found
	*/
	public PaymentFeeInfo fetchByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<PaymentFeeInfo> orderByComparator);

	/**
	* Returns the payment fee infos before and after the current payment fee info in the ordered set where uuid = &#63;.
	*
	* @param paymentFeeInfoId the primary key of the current payment fee info
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next payment fee info
	* @throws NoSuchPaymentFeeInfoException if a payment fee info with the primary key could not be found
	*/
	public PaymentFeeInfo[] findByUuid_PrevAndNext(long paymentFeeInfoId,
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<PaymentFeeInfo> orderByComparator)
		throws NoSuchPaymentFeeInfoException;

	/**
	* Removes all the payment fee infos where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(String uuid);

	/**
	* Returns the number of payment fee infos where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching payment fee infos
	*/
	public int countByUuid(String uuid);

	/**
	* Returns the payment fee info where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchPaymentFeeInfoException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching payment fee info
	* @throws NoSuchPaymentFeeInfoException if a matching payment fee info could not be found
	*/
	public PaymentFeeInfo findByUUID_G(String uuid, long groupId)
		throws NoSuchPaymentFeeInfoException;

	/**
	* Returns the payment fee info where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching payment fee info, or <code>null</code> if a matching payment fee info could not be found
	*/
	public PaymentFeeInfo fetchByUUID_G(String uuid, long groupId);

	/**
	* Returns the payment fee info where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching payment fee info, or <code>null</code> if a matching payment fee info could not be found
	*/
	public PaymentFeeInfo fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the payment fee info where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the payment fee info that was removed
	*/
	public PaymentFeeInfo removeByUUID_G(String uuid, long groupId)
		throws NoSuchPaymentFeeInfoException;

	/**
	* Returns the number of payment fee infos where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching payment fee infos
	*/
	public int countByUUID_G(String uuid, long groupId);

	/**
	* Returns all the payment fee infos where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching payment fee infos
	*/
	public java.util.List<PaymentFeeInfo> findByUuid_C(String uuid,
		long companyId);

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
	public java.util.List<PaymentFeeInfo> findByUuid_C(String uuid,
		long companyId, int start, int end);

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
	public java.util.List<PaymentFeeInfo> findByUuid_C(String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PaymentFeeInfo> orderByComparator);

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
	public java.util.List<PaymentFeeInfo> findByUuid_C(String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PaymentFeeInfo> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first payment fee info in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching payment fee info
	* @throws NoSuchPaymentFeeInfoException if a matching payment fee info could not be found
	*/
	public PaymentFeeInfo findByUuid_C_First(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<PaymentFeeInfo> orderByComparator)
		throws NoSuchPaymentFeeInfoException;

	/**
	* Returns the first payment fee info in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching payment fee info, or <code>null</code> if a matching payment fee info could not be found
	*/
	public PaymentFeeInfo fetchByUuid_C_First(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<PaymentFeeInfo> orderByComparator);

	/**
	* Returns the last payment fee info in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching payment fee info
	* @throws NoSuchPaymentFeeInfoException if a matching payment fee info could not be found
	*/
	public PaymentFeeInfo findByUuid_C_Last(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<PaymentFeeInfo> orderByComparator)
		throws NoSuchPaymentFeeInfoException;

	/**
	* Returns the last payment fee info in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching payment fee info, or <code>null</code> if a matching payment fee info could not be found
	*/
	public PaymentFeeInfo fetchByUuid_C_Last(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<PaymentFeeInfo> orderByComparator);

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
	public PaymentFeeInfo[] findByUuid_C_PrevAndNext(long paymentFeeInfoId,
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<PaymentFeeInfo> orderByComparator)
		throws NoSuchPaymentFeeInfoException;

	/**
	* Removes all the payment fee infos where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(String uuid, long companyId);

	/**
	* Returns the number of payment fee infos where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching payment fee infos
	*/
	public int countByUuid_C(String uuid, long companyId);

	/**
	* Returns all the payment fee infos where serviceConfigMappingId = &#63;.
	*
	* @param serviceConfigMappingId the service config mapping ID
	* @return the matching payment fee infos
	*/
	public java.util.List<PaymentFeeInfo> findByServiceConfigMappingId(
		long serviceConfigMappingId);

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
	public java.util.List<PaymentFeeInfo> findByServiceConfigMappingId(
		long serviceConfigMappingId, int start, int end);

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
	public java.util.List<PaymentFeeInfo> findByServiceConfigMappingId(
		long serviceConfigMappingId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PaymentFeeInfo> orderByComparator);

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
	public java.util.List<PaymentFeeInfo> findByServiceConfigMappingId(
		long serviceConfigMappingId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PaymentFeeInfo> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first payment fee info in the ordered set where serviceConfigMappingId = &#63;.
	*
	* @param serviceConfigMappingId the service config mapping ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching payment fee info
	* @throws NoSuchPaymentFeeInfoException if a matching payment fee info could not be found
	*/
	public PaymentFeeInfo findByServiceConfigMappingId_First(
		long serviceConfigMappingId,
		com.liferay.portal.kernel.util.OrderByComparator<PaymentFeeInfo> orderByComparator)
		throws NoSuchPaymentFeeInfoException;

	/**
	* Returns the first payment fee info in the ordered set where serviceConfigMappingId = &#63;.
	*
	* @param serviceConfigMappingId the service config mapping ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching payment fee info, or <code>null</code> if a matching payment fee info could not be found
	*/
	public PaymentFeeInfo fetchByServiceConfigMappingId_First(
		long serviceConfigMappingId,
		com.liferay.portal.kernel.util.OrderByComparator<PaymentFeeInfo> orderByComparator);

	/**
	* Returns the last payment fee info in the ordered set where serviceConfigMappingId = &#63;.
	*
	* @param serviceConfigMappingId the service config mapping ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching payment fee info
	* @throws NoSuchPaymentFeeInfoException if a matching payment fee info could not be found
	*/
	public PaymentFeeInfo findByServiceConfigMappingId_Last(
		long serviceConfigMappingId,
		com.liferay.portal.kernel.util.OrderByComparator<PaymentFeeInfo> orderByComparator)
		throws NoSuchPaymentFeeInfoException;

	/**
	* Returns the last payment fee info in the ordered set where serviceConfigMappingId = &#63;.
	*
	* @param serviceConfigMappingId the service config mapping ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching payment fee info, or <code>null</code> if a matching payment fee info could not be found
	*/
	public PaymentFeeInfo fetchByServiceConfigMappingId_Last(
		long serviceConfigMappingId,
		com.liferay.portal.kernel.util.OrderByComparator<PaymentFeeInfo> orderByComparator);

	/**
	* Returns the payment fee infos before and after the current payment fee info in the ordered set where serviceConfigMappingId = &#63;.
	*
	* @param paymentFeeInfoId the primary key of the current payment fee info
	* @param serviceConfigMappingId the service config mapping ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next payment fee info
	* @throws NoSuchPaymentFeeInfoException if a payment fee info with the primary key could not be found
	*/
	public PaymentFeeInfo[] findByServiceConfigMappingId_PrevAndNext(
		long paymentFeeInfoId, long serviceConfigMappingId,
		com.liferay.portal.kernel.util.OrderByComparator<PaymentFeeInfo> orderByComparator)
		throws NoSuchPaymentFeeInfoException;

	/**
	* Removes all the payment fee infos where serviceConfigMappingId = &#63; from the database.
	*
	* @param serviceConfigMappingId the service config mapping ID
	*/
	public void removeByServiceConfigMappingId(long serviceConfigMappingId);

	/**
	* Returns the number of payment fee infos where serviceConfigMappingId = &#63;.
	*
	* @param serviceConfigMappingId the service config mapping ID
	* @return the number of matching payment fee infos
	*/
	public int countByServiceConfigMappingId(long serviceConfigMappingId);

	/**
	* Caches the payment fee info in the entity cache if it is enabled.
	*
	* @param paymentFeeInfo the payment fee info
	*/
	public void cacheResult(PaymentFeeInfo paymentFeeInfo);

	/**
	* Caches the payment fee infos in the entity cache if it is enabled.
	*
	* @param paymentFeeInfos the payment fee infos
	*/
	public void cacheResult(java.util.List<PaymentFeeInfo> paymentFeeInfos);

	/**
	* Creates a new payment fee info with the primary key. Does not add the payment fee info to the database.
	*
	* @param paymentFeeInfoId the primary key for the new payment fee info
	* @return the new payment fee info
	*/
	public PaymentFeeInfo create(long paymentFeeInfoId);

	/**
	* Removes the payment fee info with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param paymentFeeInfoId the primary key of the payment fee info
	* @return the payment fee info that was removed
	* @throws NoSuchPaymentFeeInfoException if a payment fee info with the primary key could not be found
	*/
	public PaymentFeeInfo remove(long paymentFeeInfoId)
		throws NoSuchPaymentFeeInfoException;

	public PaymentFeeInfo updateImpl(PaymentFeeInfo paymentFeeInfo);

	/**
	* Returns the payment fee info with the primary key or throws a {@link NoSuchPaymentFeeInfoException} if it could not be found.
	*
	* @param paymentFeeInfoId the primary key of the payment fee info
	* @return the payment fee info
	* @throws NoSuchPaymentFeeInfoException if a payment fee info with the primary key could not be found
	*/
	public PaymentFeeInfo findByPrimaryKey(long paymentFeeInfoId)
		throws NoSuchPaymentFeeInfoException;

	/**
	* Returns the payment fee info with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param paymentFeeInfoId the primary key of the payment fee info
	* @return the payment fee info, or <code>null</code> if a payment fee info with the primary key could not be found
	*/
	public PaymentFeeInfo fetchByPrimaryKey(long paymentFeeInfoId);

	@Override
	public java.util.Map<java.io.Serializable, PaymentFeeInfo> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the payment fee infos.
	*
	* @return the payment fee infos
	*/
	public java.util.List<PaymentFeeInfo> findAll();

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
	public java.util.List<PaymentFeeInfo> findAll(int start, int end);

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
	public java.util.List<PaymentFeeInfo> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PaymentFeeInfo> orderByComparator);

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
	public java.util.List<PaymentFeeInfo> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PaymentFeeInfo> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the payment fee infos from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of payment fee infos.
	*
	* @return the number of payment fee infos
	*/
	public int countAll();

	@Override
	public java.util.Set<String> getBadColumnNames();
}