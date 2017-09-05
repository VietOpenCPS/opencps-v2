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

import org.opencps.backend.dossiermgt.model.PaymentFile;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing PaymentFile in entity cache.
 *
 * @author huymq
 * @see PaymentFile
 * @generated
 */
@ProviderType
public class PaymentFileCacheModel implements CacheModel<PaymentFile>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof PaymentFileCacheModel)) {
			return false;
		}

		PaymentFileCacheModel paymentFileCacheModel = (PaymentFileCacheModel)obj;

		if (paymentFileId == paymentFileCacheModel.paymentFileId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, paymentFileId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(73);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", paymentFileId=");
		sb.append(paymentFileId);
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
		sb.append(", isNew=");
		sb.append(isNew);
		sb.append(", paymentFee=");
		sb.append(paymentFee);
		sb.append(", paymentAmount=");
		sb.append(paymentAmount);
		sb.append(", paymentNote=");
		sb.append(paymentNote);
		sb.append(", paymentOptions=");
		sb.append(paymentOptions);
		sb.append(", keypayUrl=");
		sb.append(keypayUrl);
		sb.append(", keypayTransactionId=");
		sb.append(keypayTransactionId);
		sb.append(", keypayGoodCode=");
		sb.append(keypayGoodCode);
		sb.append(", keypayMerchantCode=");
		sb.append(keypayMerchantCode);
		sb.append(", bankInfo=");
		sb.append(bankInfo);
		sb.append(", paymentStatus=");
		sb.append(paymentStatus);
		sb.append(", paymentMethod=");
		sb.append(paymentMethod);
		sb.append(", confirmDatetime=");
		sb.append(confirmDatetime);
		sb.append(", confirmFileEntryId=");
		sb.append(confirmFileEntryId);
		sb.append(", confirmNote=");
		sb.append(confirmNote);
		sb.append(", approveDatetime=");
		sb.append(approveDatetime);
		sb.append(", accountUserName=");
		sb.append(accountUserName);
		sb.append(", govAgencyTaxNo=");
		sb.append(govAgencyTaxNo);
		sb.append(", invoiceTemplateNo=");
		sb.append(invoiceTemplateNo);
		sb.append(", invoiceIssueNo=");
		sb.append(invoiceIssueNo);
		sb.append(", invoiceNo=");
		sb.append(invoiceNo);
		sb.append(", invoiceFileEntryId=");
		sb.append(invoiceFileEntryId);
		sb.append(", paymentGateStatusCode=");
		sb.append(paymentGateStatusCode);
		sb.append(", paymentGateCheckCode=");
		sb.append(paymentGateCheckCode);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public PaymentFile toEntityModel() {
		PaymentFileImpl paymentFileImpl = new PaymentFileImpl();

		if (uuid == null) {
			paymentFileImpl.setUuid(StringPool.BLANK);
		}
		else {
			paymentFileImpl.setUuid(uuid);
		}

		paymentFileImpl.setPaymentFileId(paymentFileId);
		paymentFileImpl.setGroupId(groupId);
		paymentFileImpl.setCompanyId(companyId);
		paymentFileImpl.setUserId(userId);

		if (userName == null) {
			paymentFileImpl.setUserName(StringPool.BLANK);
		}
		else {
			paymentFileImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			paymentFileImpl.setCreateDate(null);
		}
		else {
			paymentFileImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			paymentFileImpl.setModifiedDate(null);
		}
		else {
			paymentFileImpl.setModifiedDate(new Date(modifiedDate));
		}

		paymentFileImpl.setDossierId(dossierId);

		if (referenceUid == null) {
			paymentFileImpl.setReferenceUid(StringPool.BLANK);
		}
		else {
			paymentFileImpl.setReferenceUid(referenceUid);
		}

		if (govAgencyCode == null) {
			paymentFileImpl.setGovAgencyCode(StringPool.BLANK);
		}
		else {
			paymentFileImpl.setGovAgencyCode(govAgencyCode);
		}

		if (govAgencyName == null) {
			paymentFileImpl.setGovAgencyName(StringPool.BLANK);
		}
		else {
			paymentFileImpl.setGovAgencyName(govAgencyName);
		}

		paymentFileImpl.setIsNew(isNew);

		if (paymentFee == null) {
			paymentFileImpl.setPaymentFee(StringPool.BLANK);
		}
		else {
			paymentFileImpl.setPaymentFee(paymentFee);
		}

		paymentFileImpl.setPaymentAmount(paymentAmount);

		if (paymentNote == null) {
			paymentFileImpl.setPaymentNote(StringPool.BLANK);
		}
		else {
			paymentFileImpl.setPaymentNote(paymentNote);
		}

		if (paymentOptions == null) {
			paymentFileImpl.setPaymentOptions(StringPool.BLANK);
		}
		else {
			paymentFileImpl.setPaymentOptions(paymentOptions);
		}

		if (keypayUrl == Long.MIN_VALUE) {
			paymentFileImpl.setKeypayUrl(null);
		}
		else {
			paymentFileImpl.setKeypayUrl(new Date(keypayUrl));
		}

		paymentFileImpl.setKeypayTransactionId(keypayTransactionId);

		if (keypayGoodCode == Long.MIN_VALUE) {
			paymentFileImpl.setKeypayGoodCode(null);
		}
		else {
			paymentFileImpl.setKeypayGoodCode(new Date(keypayGoodCode));
		}

		if (keypayMerchantCode == Long.MIN_VALUE) {
			paymentFileImpl.setKeypayMerchantCode(null);
		}
		else {
			paymentFileImpl.setKeypayMerchantCode(new Date(keypayMerchantCode));
		}

		if (bankInfo == Long.MIN_VALUE) {
			paymentFileImpl.setBankInfo(null);
		}
		else {
			paymentFileImpl.setBankInfo(new Date(bankInfo));
		}

		paymentFileImpl.setPaymentStatus(paymentStatus);
		paymentFileImpl.setPaymentMethod(paymentMethod);

		if (confirmDatetime == Long.MIN_VALUE) {
			paymentFileImpl.setConfirmDatetime(null);
		}
		else {
			paymentFileImpl.setConfirmDatetime(new Date(confirmDatetime));
		}

		paymentFileImpl.setConfirmFileEntryId(confirmFileEntryId);

		if (confirmNote == null) {
			paymentFileImpl.setConfirmNote(StringPool.BLANK);
		}
		else {
			paymentFileImpl.setConfirmNote(confirmNote);
		}

		if (approveDatetime == Long.MIN_VALUE) {
			paymentFileImpl.setApproveDatetime(null);
		}
		else {
			paymentFileImpl.setApproveDatetime(new Date(approveDatetime));
		}

		if (accountUserName == null) {
			paymentFileImpl.setAccountUserName(StringPool.BLANK);
		}
		else {
			paymentFileImpl.setAccountUserName(accountUserName);
		}

		if (govAgencyTaxNo == null) {
			paymentFileImpl.setGovAgencyTaxNo(StringPool.BLANK);
		}
		else {
			paymentFileImpl.setGovAgencyTaxNo(govAgencyTaxNo);
		}

		if (invoiceTemplateNo == null) {
			paymentFileImpl.setInvoiceTemplateNo(StringPool.BLANK);
		}
		else {
			paymentFileImpl.setInvoiceTemplateNo(invoiceTemplateNo);
		}

		if (invoiceIssueNo == null) {
			paymentFileImpl.setInvoiceIssueNo(StringPool.BLANK);
		}
		else {
			paymentFileImpl.setInvoiceIssueNo(invoiceIssueNo);
		}

		if (invoiceNo == null) {
			paymentFileImpl.setInvoiceNo(StringPool.BLANK);
		}
		else {
			paymentFileImpl.setInvoiceNo(invoiceNo);
		}

		paymentFileImpl.setInvoiceFileEntryId(invoiceFileEntryId);
		paymentFileImpl.setPaymentGateStatusCode(paymentGateStatusCode);
		paymentFileImpl.setPaymentGateCheckCode(paymentGateCheckCode);

		paymentFileImpl.resetOriginalValues();

		return paymentFileImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		paymentFileId = objectInput.readLong();

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

		isNew = objectInput.readBoolean();
		paymentFee = objectInput.readUTF();

		paymentAmount = objectInput.readDouble();
		paymentNote = objectInput.readUTF();
		paymentOptions = objectInput.readUTF();
		keypayUrl = objectInput.readLong();

		keypayTransactionId = objectInput.readLong();
		keypayGoodCode = objectInput.readLong();
		keypayMerchantCode = objectInput.readLong();
		bankInfo = objectInput.readLong();

		paymentStatus = objectInput.readInt();

		paymentMethod = objectInput.readInt();
		confirmDatetime = objectInput.readLong();

		confirmFileEntryId = objectInput.readLong();
		confirmNote = objectInput.readUTF();
		approveDatetime = objectInput.readLong();
		accountUserName = objectInput.readUTF();
		govAgencyTaxNo = objectInput.readUTF();
		invoiceTemplateNo = objectInput.readUTF();
		invoiceIssueNo = objectInput.readUTF();
		invoiceNo = objectInput.readUTF();

		invoiceFileEntryId = objectInput.readLong();

		paymentGateStatusCode = objectInput.readInt();

		paymentGateCheckCode = objectInput.readInt();
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

		objectOutput.writeLong(paymentFileId);

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

		objectOutput.writeBoolean(isNew);

		if (paymentFee == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(paymentFee);
		}

		objectOutput.writeDouble(paymentAmount);

		if (paymentNote == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(paymentNote);
		}

		if (paymentOptions == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(paymentOptions);
		}

		objectOutput.writeLong(keypayUrl);

		objectOutput.writeLong(keypayTransactionId);
		objectOutput.writeLong(keypayGoodCode);
		objectOutput.writeLong(keypayMerchantCode);
		objectOutput.writeLong(bankInfo);

		objectOutput.writeInt(paymentStatus);

		objectOutput.writeInt(paymentMethod);
		objectOutput.writeLong(confirmDatetime);

		objectOutput.writeLong(confirmFileEntryId);

		if (confirmNote == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(confirmNote);
		}

		objectOutput.writeLong(approveDatetime);

		if (accountUserName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(accountUserName);
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

		if (invoiceNo == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(invoiceNo);
		}

		objectOutput.writeLong(invoiceFileEntryId);

		objectOutput.writeInt(paymentGateStatusCode);

		objectOutput.writeInt(paymentGateCheckCode);
	}

	public String uuid;
	public long paymentFileId;
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
	public boolean isNew;
	public String paymentFee;
	public double paymentAmount;
	public String paymentNote;
	public String paymentOptions;
	public long keypayUrl;
	public long keypayTransactionId;
	public long keypayGoodCode;
	public long keypayMerchantCode;
	public long bankInfo;
	public int paymentStatus;
	public int paymentMethod;
	public long confirmDatetime;
	public long confirmFileEntryId;
	public String confirmNote;
	public long approveDatetime;
	public String accountUserName;
	public String govAgencyTaxNo;
	public String invoiceTemplateNo;
	public String invoiceIssueNo;
	public String invoiceNo;
	public long invoiceFileEntryId;
	public int paymentGateStatusCode;
	public int paymentGateCheckCode;
}