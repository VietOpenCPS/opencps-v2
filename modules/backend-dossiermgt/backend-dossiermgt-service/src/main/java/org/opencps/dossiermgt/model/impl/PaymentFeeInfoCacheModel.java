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

package org.opencps.dossiermgt.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import org.opencps.dossiermgt.model.PaymentFeeInfo;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing PaymentFeeInfo in entity cache.
 *
 * @author huymq
 * @see PaymentFeeInfo
 * @generated
 */
@ProviderType
public class PaymentFeeInfoCacheModel implements CacheModel<PaymentFeeInfo>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof PaymentFeeInfoCacheModel)) {
			return false;
		}

		PaymentFeeInfoCacheModel paymentFeeInfoCacheModel = (PaymentFeeInfoCacheModel)obj;

		if (paymentFeeInfoId == paymentFeeInfoCacheModel.paymentFeeInfoId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, paymentFeeInfoId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(27);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", paymentFeeInfoId=");
		sb.append(paymentFeeInfoId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", type=");
		sb.append(type);
		sb.append(", paymentFeeCode=");
		sb.append(paymentFeeCode);
		sb.append(", paymentFeeName=");
		sb.append(paymentFeeName);
		sb.append(", amount=");
		sb.append(amount);
		sb.append(", serviceConfigMappingId=");
		sb.append(serviceConfigMappingId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public PaymentFeeInfo toEntityModel() {
		PaymentFeeInfoImpl paymentFeeInfoImpl = new PaymentFeeInfoImpl();

		if (uuid == null) {
			paymentFeeInfoImpl.setUuid("");
		}
		else {
			paymentFeeInfoImpl.setUuid(uuid);
		}

		paymentFeeInfoImpl.setPaymentFeeInfoId(paymentFeeInfoId);
		paymentFeeInfoImpl.setGroupId(groupId);
		paymentFeeInfoImpl.setCompanyId(companyId);
		paymentFeeInfoImpl.setUserId(userId);

		if (userName == null) {
			paymentFeeInfoImpl.setUserName("");
		}
		else {
			paymentFeeInfoImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			paymentFeeInfoImpl.setCreateDate(null);
		}
		else {
			paymentFeeInfoImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			paymentFeeInfoImpl.setModifiedDate(null);
		}
		else {
			paymentFeeInfoImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (type == null) {
			paymentFeeInfoImpl.setType("");
		}
		else {
			paymentFeeInfoImpl.setType(type);
		}

		if (paymentFeeCode == null) {
			paymentFeeInfoImpl.setPaymentFeeCode("");
		}
		else {
			paymentFeeInfoImpl.setPaymentFeeCode(paymentFeeCode);
		}

		if (paymentFeeName == null) {
			paymentFeeInfoImpl.setPaymentFeeName("");
		}
		else {
			paymentFeeInfoImpl.setPaymentFeeName(paymentFeeName);
		}

		if (amount == null) {
			paymentFeeInfoImpl.setAmount("");
		}
		else {
			paymentFeeInfoImpl.setAmount(amount);
		}

		paymentFeeInfoImpl.setServiceConfigMappingId(serviceConfigMappingId);

		paymentFeeInfoImpl.resetOriginalValues();

		return paymentFeeInfoImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		paymentFeeInfoId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		type = objectInput.readUTF();
		paymentFeeCode = objectInput.readUTF();
		paymentFeeName = objectInput.readUTF();
		amount = objectInput.readUTF();

		serviceConfigMappingId = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		if (uuid == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		objectOutput.writeLong(paymentFeeInfoId);

		objectOutput.writeLong(groupId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		if (type == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(type);
		}

		if (paymentFeeCode == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(paymentFeeCode);
		}

		if (paymentFeeName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(paymentFeeName);
		}

		if (amount == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(amount);
		}

		objectOutput.writeLong(serviceConfigMappingId);
	}

	public String uuid;
	public long paymentFeeInfoId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String type;
	public String paymentFeeCode;
	public String paymentFeeName;
	public String amount;
	public long serviceConfigMappingId;
}