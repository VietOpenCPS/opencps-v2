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

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.opencps.dossiermgt.exception.NoSuchPaymentConfigException;
import org.opencps.dossiermgt.model.PaymentConfig;

/**
 * The persistence interface for the payment config service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author huymq
 * @see org.opencps.dossiermgt.service.persistence.impl.PaymentConfigPersistenceImpl
 * @see PaymentConfigUtil
 * @generated
 */
@ProviderType
public interface PaymentConfigPersistence extends BasePersistence<PaymentConfig> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link PaymentConfigUtil} to access the payment config persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the payment configs where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching payment configs
	*/
	public java.util.List<PaymentConfig> findByUuid(String uuid);

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
	public java.util.List<PaymentConfig> findByUuid(String uuid, int start,
		int end);

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
	public java.util.List<PaymentConfig> findByUuid(String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<PaymentConfig> orderByComparator);

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
	public java.util.List<PaymentConfig> findByUuid(String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<PaymentConfig> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first payment config in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching payment config
	* @throws NoSuchPaymentConfigException if a matching payment config could not be found
	*/
	public PaymentConfig findByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<PaymentConfig> orderByComparator)
		throws NoSuchPaymentConfigException;

	/**
	* Returns the first payment config in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching payment config, or <code>null</code> if a matching payment config could not be found
	*/
	public PaymentConfig fetchByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<PaymentConfig> orderByComparator);

	/**
	* Returns the last payment config in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching payment config
	* @throws NoSuchPaymentConfigException if a matching payment config could not be found
	*/
	public PaymentConfig findByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<PaymentConfig> orderByComparator)
		throws NoSuchPaymentConfigException;

	/**
	* Returns the last payment config in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching payment config, or <code>null</code> if a matching payment config could not be found
	*/
	public PaymentConfig fetchByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<PaymentConfig> orderByComparator);

	/**
	* Returns the payment configs before and after the current payment config in the ordered set where uuid = &#63;.
	*
	* @param paymentConfigId the primary key of the current payment config
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next payment config
	* @throws NoSuchPaymentConfigException if a payment config with the primary key could not be found
	*/
	public PaymentConfig[] findByUuid_PrevAndNext(long paymentConfigId,
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<PaymentConfig> orderByComparator)
		throws NoSuchPaymentConfigException;

	/**
	* Removes all the payment configs where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(String uuid);

	/**
	* Returns the number of payment configs where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching payment configs
	*/
	public int countByUuid(String uuid);

	/**
	* Returns the payment config where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchPaymentConfigException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching payment config
	* @throws NoSuchPaymentConfigException if a matching payment config could not be found
	*/
	public PaymentConfig findByUUID_G(String uuid, long groupId)
		throws NoSuchPaymentConfigException;

	/**
	* Returns the payment config where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching payment config, or <code>null</code> if a matching payment config could not be found
	*/
	public PaymentConfig fetchByUUID_G(String uuid, long groupId);

	/**
	* Returns the payment config where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching payment config, or <code>null</code> if a matching payment config could not be found
	*/
	public PaymentConfig fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the payment config where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the payment config that was removed
	*/
	public PaymentConfig removeByUUID_G(String uuid, long groupId)
		throws NoSuchPaymentConfigException;

	/**
	* Returns the number of payment configs where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching payment configs
	*/
	public int countByUUID_G(String uuid, long groupId);

	/**
	* Returns all the payment configs where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching payment configs
	*/
	public java.util.List<PaymentConfig> findByUuid_C(String uuid,
		long companyId);

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
	public java.util.List<PaymentConfig> findByUuid_C(String uuid,
		long companyId, int start, int end);

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
	public java.util.List<PaymentConfig> findByUuid_C(String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PaymentConfig> orderByComparator);

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
	public java.util.List<PaymentConfig> findByUuid_C(String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PaymentConfig> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first payment config in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching payment config
	* @throws NoSuchPaymentConfigException if a matching payment config could not be found
	*/
	public PaymentConfig findByUuid_C_First(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<PaymentConfig> orderByComparator)
		throws NoSuchPaymentConfigException;

	/**
	* Returns the first payment config in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching payment config, or <code>null</code> if a matching payment config could not be found
	*/
	public PaymentConfig fetchByUuid_C_First(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<PaymentConfig> orderByComparator);

	/**
	* Returns the last payment config in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching payment config
	* @throws NoSuchPaymentConfigException if a matching payment config could not be found
	*/
	public PaymentConfig findByUuid_C_Last(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<PaymentConfig> orderByComparator)
		throws NoSuchPaymentConfigException;

	/**
	* Returns the last payment config in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching payment config, or <code>null</code> if a matching payment config could not be found
	*/
	public PaymentConfig fetchByUuid_C_Last(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<PaymentConfig> orderByComparator);

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
	public PaymentConfig[] findByUuid_C_PrevAndNext(long paymentConfigId,
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<PaymentConfig> orderByComparator)
		throws NoSuchPaymentConfigException;

	/**
	* Removes all the payment configs where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(String uuid, long companyId);

	/**
	* Returns the number of payment configs where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching payment configs
	*/
	public int countByUuid_C(String uuid, long companyId);

	/**
	* Returns all the payment configs where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching payment configs
	*/
	public java.util.List<PaymentConfig> findByFB_GID(long groupId);

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
	public java.util.List<PaymentConfig> findByFB_GID(long groupId, int start,
		int end);

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
	public java.util.List<PaymentConfig> findByFB_GID(long groupId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<PaymentConfig> orderByComparator);

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
	public java.util.List<PaymentConfig> findByFB_GID(long groupId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<PaymentConfig> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first payment config in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching payment config
	* @throws NoSuchPaymentConfigException if a matching payment config could not be found
	*/
	public PaymentConfig findByFB_GID_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<PaymentConfig> orderByComparator)
		throws NoSuchPaymentConfigException;

	/**
	* Returns the first payment config in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching payment config, or <code>null</code> if a matching payment config could not be found
	*/
	public PaymentConfig fetchByFB_GID_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<PaymentConfig> orderByComparator);

	/**
	* Returns the last payment config in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching payment config
	* @throws NoSuchPaymentConfigException if a matching payment config could not be found
	*/
	public PaymentConfig findByFB_GID_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<PaymentConfig> orderByComparator)
		throws NoSuchPaymentConfigException;

	/**
	* Returns the last payment config in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching payment config, or <code>null</code> if a matching payment config could not be found
	*/
	public PaymentConfig fetchByFB_GID_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<PaymentConfig> orderByComparator);

	/**
	* Returns the payment configs before and after the current payment config in the ordered set where groupId = &#63;.
	*
	* @param paymentConfigId the primary key of the current payment config
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next payment config
	* @throws NoSuchPaymentConfigException if a payment config with the primary key could not be found
	*/
	public PaymentConfig[] findByFB_GID_PrevAndNext(long paymentConfigId,
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<PaymentConfig> orderByComparator)
		throws NoSuchPaymentConfigException;

	/**
	* Removes all the payment configs where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public void removeByFB_GID(long groupId);

	/**
	* Returns the number of payment configs where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching payment configs
	*/
	public int countByFB_GID(long groupId);

	/**
	* Returns the payment config where groupId = &#63; and govAgencyCode = &#63; or throws a {@link NoSuchPaymentConfigException} if it could not be found.
	*
	* @param groupId the group ID
	* @param govAgencyCode the gov agency code
	* @return the matching payment config
	* @throws NoSuchPaymentConfigException if a matching payment config could not be found
	*/
	public PaymentConfig findByFB_GID_govAgencyCode(long groupId,
		String govAgencyCode) throws NoSuchPaymentConfigException;

	/**
	* Returns the payment config where groupId = &#63; and govAgencyCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param govAgencyCode the gov agency code
	* @return the matching payment config, or <code>null</code> if a matching payment config could not be found
	*/
	public PaymentConfig fetchByFB_GID_govAgencyCode(long groupId,
		String govAgencyCode);

	/**
	* Returns the payment config where groupId = &#63; and govAgencyCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param govAgencyCode the gov agency code
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching payment config, or <code>null</code> if a matching payment config could not be found
	*/
	public PaymentConfig fetchByFB_GID_govAgencyCode(long groupId,
		String govAgencyCode, boolean retrieveFromCache);

	/**
	* Removes the payment config where groupId = &#63; and govAgencyCode = &#63; from the database.
	*
	* @param groupId the group ID
	* @param govAgencyCode the gov agency code
	* @return the payment config that was removed
	*/
	public PaymentConfig removeByFB_GID_govAgencyCode(long groupId,
		String govAgencyCode) throws NoSuchPaymentConfigException;

	/**
	* Returns the number of payment configs where groupId = &#63; and govAgencyCode = &#63;.
	*
	* @param groupId the group ID
	* @param govAgencyCode the gov agency code
	* @return the number of matching payment configs
	*/
	public int countByFB_GID_govAgencyCode(long groupId, String govAgencyCode);

	/**
	* Caches the payment config in the entity cache if it is enabled.
	*
	* @param paymentConfig the payment config
	*/
	public void cacheResult(PaymentConfig paymentConfig);

	/**
	* Caches the payment configs in the entity cache if it is enabled.
	*
	* @param paymentConfigs the payment configs
	*/
	public void cacheResult(java.util.List<PaymentConfig> paymentConfigs);

	/**
	* Creates a new payment config with the primary key. Does not add the payment config to the database.
	*
	* @param paymentConfigId the primary key for the new payment config
	* @return the new payment config
	*/
	public PaymentConfig create(long paymentConfigId);

	/**
	* Removes the payment config with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param paymentConfigId the primary key of the payment config
	* @return the payment config that was removed
	* @throws NoSuchPaymentConfigException if a payment config with the primary key could not be found
	*/
	public PaymentConfig remove(long paymentConfigId)
		throws NoSuchPaymentConfigException;

	public PaymentConfig updateImpl(PaymentConfig paymentConfig);

	/**
	* Returns the payment config with the primary key or throws a {@link NoSuchPaymentConfigException} if it could not be found.
	*
	* @param paymentConfigId the primary key of the payment config
	* @return the payment config
	* @throws NoSuchPaymentConfigException if a payment config with the primary key could not be found
	*/
	public PaymentConfig findByPrimaryKey(long paymentConfigId)
		throws NoSuchPaymentConfigException;

	/**
	* Returns the payment config with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param paymentConfigId the primary key of the payment config
	* @return the payment config, or <code>null</code> if a payment config with the primary key could not be found
	*/
	public PaymentConfig fetchByPrimaryKey(long paymentConfigId);

	@Override
	public java.util.Map<java.io.Serializable, PaymentConfig> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the payment configs.
	*
	* @return the payment configs
	*/
	public java.util.List<PaymentConfig> findAll();

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
	public java.util.List<PaymentConfig> findAll(int start, int end);

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
	public java.util.List<PaymentConfig> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PaymentConfig> orderByComparator);

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
	public java.util.List<PaymentConfig> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PaymentConfig> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the payment configs from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of payment configs.
	*
	* @return the number of payment configs
	*/
	public int countAll();

	@Override
	public java.util.Set<String> getBadColumnNames();
}