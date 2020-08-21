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

package org.opencps.dossiermgt.service.impl;

import java.util.Date;
import java.util.List;

import org.opencps.dossiermgt.model.PaymentFeeInfo;
import org.opencps.dossiermgt.service.base.PaymentFeeInfoLocalServiceBaseImpl;

import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;


/**
 * The implementation of the payment fee info local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link org.opencps.dossiermgt.service.PaymentFeeInfoLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author huymq
 * @see PaymentFeeInfoLocalServiceBaseImpl
 * @see org.opencps.dossiermgt.service.PaymentFeeInfoLocalServiceUtil
 */
public class PaymentFeeInfoLocalServiceImpl
	extends PaymentFeeInfoLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link org.opencps.dossiermgt.service.PaymentFeeInfoLocalServiceUtil} to access the payment fee info local service.
	 */
	
	public PaymentFeeInfo addPaymentFeeInfo(long groupId, long serviceConfigMappingId, String paymentFeeCode,
			String paymentFeeName, String type, String amount, ServiceContext context) {

		long paymentFeeInfoId = counterLocalService.increment(PaymentFeeInfo.class.getName());

		PaymentFeeInfo paymentFeeInfo = paymentFeeInfoPersistence.create(paymentFeeInfoId);

		Date now = new Date();

		long userId = context.getUserId();

		User user = userPersistence.fetchByPrimaryKey(userId);

		// common field service config mapping
		paymentFeeInfo.setCreateDate(now);
		paymentFeeInfo.setModifiedDate(now);
		paymentFeeInfo.setCompanyId(context.getCompanyId());
		paymentFeeInfo.setGroupId(groupId);
		paymentFeeInfo.setUserName(user.getFullName());

		paymentFeeInfo.setPaymentFeeCode(paymentFeeCode);
		paymentFeeInfo.setPaymentFeeName(paymentFeeName);
		paymentFeeInfo.setType(type);
		paymentFeeInfo.setAmount(amount);

		paymentFeeInfo.setServiceConfigMappingId(serviceConfigMappingId);

		return paymentFeeInfoPersistence.update(paymentFeeInfo);
	}
	
	public List<PaymentFeeInfo> findByServiceConfigMappingId(long serviceConfigMappingId){
		return paymentFeeInfoPersistence.findByServiceConfigMappingId(serviceConfigMappingId);
	}
}