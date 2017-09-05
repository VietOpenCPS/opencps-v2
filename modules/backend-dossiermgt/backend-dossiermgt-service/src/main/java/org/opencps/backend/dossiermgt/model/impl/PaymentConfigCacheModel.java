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

package org.opencps.backend.dossiermgt.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import org.opencps.backend.dossiermgt.model.PaymentConfig;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing PaymentConfig in entity cache.
 *
 * @author huymq
 * @see PaymentConfig
 * @generated
 */
@ProviderType
public class PaymentConfigCacheModel implements CacheModel<PaymentConfig>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof PaymentConfigCacheModel)) {
			return false;
		}

		PaymentConfigCacheModel paymentConfigCacheModel = (PaymentConfigCacheModel)obj;

		if (paymentConfigId == paymentConfigCacheModel.paymentConfigId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, paymentConfigId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(53);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", paymentConfigId=");
		sb.append(paymentConfigId);
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
		sb.append(", dossierId=");
		sb.append(dossierId);
		sb.append(", referenceUid=");
		sb.append(referenceUid);
		sb.append(", govAgencyCode=");
		sb.append(govAgencyCode);
		sb.append(", govAgencyName=");
		sb.append(govAgencyName);
		sb.append(", govAgencyTaxNo=");
		sb.append(govAgencyTaxNo);
		sb.append(", invoiceTemplateNo=");
		sb.append(invoiceTemplateNo);
		sb.append(", invoiceIssueNo=");
		sb.append(invoiceIssueNo);
		sb.append(", invoiceLastNo=");
		sb.append(invoiceLastNo);
		sb.append(", bankInfo=");
		sb.append(bankInfo);
		sb.append(", placeInfo=");
		sb.append(placeInfo);
		sb.append(", paymentDomain=");
		sb.append(paymentDomain);
		sb.append(", paymentVersion=");
		sb.append(paymentVersion);
		sb.append(", paymentMerchantCode=");
		sb.append(paymentMerchantCode);
		sb.append(", paymentSecureKey=");
		sb.append(paymentSecureKey);
		sb.append(", reportTemplate=");
		sb.append(reportTemplate);
		sb.append(", paymentGateType=");
		sb.append(paymentGateType);
		sb.append(", returnUrl=");
		sb.append(returnUrl);
		sb.append(", paymentConfigNo=");
		sb.append(paymentConfigNo);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public PaymentConfig toEntityModel() {
		PaymentConfigImpl paymentConfigImpl = new PaymentConfigImpl();

		if (uuid == null) {
			paymentConfigImpl.setUuid(StringPool.BLANK);
		}
		else {
			paymentConfigImpl.setUuid(uuid);
		}

		paymentConfigImpl.setPaymentConfigId(paymentConfigId);
		paymentConfigImpl.setGroupId(groupId);
		paymentConfigImpl.setCompanyId(companyId);
		paymentConfigImpl.setUserId(userId);

		if (userName == null) {
			paymentConfigImpl.setUserName(StringPool.BLANK);
		}
		else {
			paymentConfigImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			paymentConfigImpl.setCreateDate(null);
		}
		else {
			paymentConfigImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			paymentConfigImpl.setModifiedDate(null);
		}
		else {
			paymentConfigImpl.setModifiedDate(new Date(modifiedDate));
		}

		paymentConfigImpl.setDossierId(dossierId);

		if (referenceUid == null) {
			paymentConfigImpl.setReferenceUid(StringPool.BLANK);
		}
		else {
			paymentConfigImpl.setReferenceUid(referenceUid);
		}

		if (govAgencyCode == null) {
			paymentConfigImpl.setGovAgencyCode(StringPool.BLANK);
		}
		else {
			paymentConfigImpl.setGovAgencyCode(govAgencyCode);
		}

		if (govAgencyName == null) {
			paymentConfigImpl.setGovAgencyName(StringPool.BLANK);
		}
		else {
			paymentConfigImpl.setGovAgencyName(govAgencyName);
		}

		if (govAgencyTaxNo == null) {
			paymentConfigImpl.setGovAgencyTaxNo(StringPool.BLANK);
		}
		else {
			paymentConfigImpl.setGovAgencyTaxNo(govAgencyTaxNo);
		}

		if (invoiceTemplateNo == null) {
			paymentConfigImpl.setInvoiceTemplateNo(StringPool.BLANK);
		}
		else {
			paymentConfigImpl.setInvoiceTemplateNo(invoiceTemplateNo);
		}

		if (invoiceIssueNo == null) {
			paymentConfigImpl.setInvoiceIssueNo(StringPool.BLANK);
		}
		else {
			paymentConfigImpl.setInvoiceIssueNo(invoiceIssueNo);
		}

		if (invoiceLastNo == null) {
			paymentConfigImpl.setInvoiceLastNo(StringPool.BLANK);
		}
		else {
			paymentConfigImpl.setInvoiceLastNo(invoiceLastNo);
		}

		if (bankInfo == null) {
			paymentConfigImpl.setBankInfo(StringPool.BLANK);
		}
		else {
			paymentConfigImpl.setBankInfo(bankInfo);
		}

		if (placeInfo == null) {
			paymentConfigImpl.setPlaceInfo(StringPool.BLANK);
		}
		else {
			paymentConfigImpl.setPlaceInfo(placeInfo);
		}

		if (paymentDomain == null) {
			paymentConfigImpl.setPaymentDomain(StringPool.BLANK);
		}
		else {
			paymentConfigImpl.setPaymentDomain(paymentDomain);
		}

		if (paymentVersion == null) {
			paymentConfigImpl.setPaymentVersion(StringPool.BLANK);
		}
		else {
			paymentConfigImpl.setPaymentVersion(paymentVersion);
		}

		if (paymentMerchantCode == null) {
			paymentConfigImpl.setPaymentMerchantCode(StringPool.BLANK);
		}
		else {
			paymentConfigImpl.setPaymentMerchantCode(paymentMerchantCode);
		}

		if (paymentSecureKey == null) {
			paymentConfigImpl.setPaymentSecureKey(StringPool.BLANK);
		}
		else {
			paymentConfigImpl.setPaymentSecureKey(paymentSecureKey);
		}

		if (reportTemplate == null) {
			paymentConfigImpl.setReportTemplate(StringPool.BLANK);
		}
		else {
			paymentConfigImpl.setReportTemplate(reportTemplate);
		}

		paymentConfigImpl.setPaymentGateType(paymentGateType);

		if (returnUrl == null) {
			paymentConfigImpl.setReturnUrl(StringPool.BLANK);
		}
		else {
			paymentConfigImpl.setReturnUrl(returnUrl);
		}

		if (paymentConfigNo == null) {
			paymentConfigImpl.setPaymentConfigNo(StringPool.BLANK);
		}
		else {
			paymentConfigImpl.setPaymentConfigNo(paymentConfigNo);
		}

		paymentConfigImpl.resetOriginalValues();

		return paymentConfigImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		paymentConfigId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		dossierId = objectInput.readLong();
		referenceUid = objectInput.readUTF();
		govAgencyCode = objectInput.readUTF();
		govAgencyName = objectInput.readUTF();
		govAgencyTaxNo = objectInput.readUTF();
		invoiceTemplateNo = objectInput.readUTF();
		invoiceIssueNo = objectInput.readUTF();
		invoiceLastNo = objectInput.readUTF();
		bankInfo = objectInput.readUTF();
		placeInfo = objectInput.readUTF();
		paymentDomain = objectInput.readUTF();
		paymentVersion = objectInput.readUTF();
		paymentMerchantCode = objectInput.readUTF();
		paymentSecureKey = objectInput.readUTF();
		reportTemplate = objectInput.readUTF();

		paymentGateType = objectInput.readLong();
		returnUrl = objectInput.readUTF();
		paymentConfigNo = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		if (uuid == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		objectOutput.writeLong(paymentConfigId);

		objectOutput.writeLong(groupId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		objectOutput.writeLong(dossierId);

		if (referenceUid == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(referenceUid);
		}

		if (govAgencyCode == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(govAgencyCode);
		}

		if (govAgencyName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(govAgencyName);
		}

		if (govAgencyTaxNo == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(govAgencyTaxNo);
		}

		if (invoiceTemplateNo == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(invoiceTemplateNo);
		}

		if (invoiceIssueNo == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(invoiceIssueNo);
		}

		if (invoiceLastNo == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(invoiceLastNo);
		}

		if (bankInfo == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(bankInfo);
		}

		if (placeInfo == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(placeInfo);
		}

		if (paymentDomain == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(paymentDomain);
		}

		if (paymentVersion == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(paymentVersion);
		}

		if (paymentMerchantCode == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(paymentMerchantCode);
		}

		if (paymentSecureKey == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(paymentSecureKey);
		}

		if (reportTemplate == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(reportTemplate);
		}

		objectOutput.writeLong(paymentGateType);

		if (returnUrl == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(returnUrl);
		}

		if (paymentConfigNo == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(paymentConfigNo);
		}
	}

	public String uuid;
	public long paymentConfigId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long dossierId;
	public String referenceUid;
	public String govAgencyCode;
	public String govAgencyName;
	public String govAgencyTaxNo;
	public String invoiceTemplateNo;
	public String invoiceIssueNo;
	public String invoiceLastNo;
	public String bankInfo;
	public String placeInfo;
	public String paymentDomain;
	public String paymentVersion;
	public String paymentMerchantCode;
	public String paymentSecureKey;
	public String reportTemplate;
	public long paymentGateType;
	public String returnUrl;
	public String paymentConfigNo;
}