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

package org.opencps.backend.dossiermgt.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.exportimport.kernel.lar.StagedModelType;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link PaymentFile}.
 * </p>
 *
 * @author huymq
 * @see PaymentFile
 * @generated
 */
@ProviderType
public class PaymentFileWrapper implements PaymentFile,
	ModelWrapper<PaymentFile> {
	public PaymentFileWrapper(PaymentFile paymentFile) {
		_paymentFile = paymentFile;
	}

	@Override
	public Class<?> getModelClass() {
		return PaymentFile.class;
	}

	@Override
	public String getModelClassName() {
		return PaymentFile.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("paymentFileId", getPaymentFileId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("dossierId", getDossierId());
		attributes.put("referenceUid", getReferenceUid());
		attributes.put("govAgencyCode", getGovAgencyCode());
		attributes.put("govAgencyName", getGovAgencyName());
		attributes.put("isNew", getIsNew());
		attributes.put("paymentFee", getPaymentFee());
		attributes.put("paymentAmount", getPaymentAmount());
		attributes.put("paymentNote", getPaymentNote());
		attributes.put("paymentOptions", getPaymentOptions());
		attributes.put("keypayUrl", getKeypayUrl());
		attributes.put("keypayTransactionId", getKeypayTransactionId());
		attributes.put("keypayGoodCode", getKeypayGoodCode());
		attributes.put("keypayMerchantCode", getKeypayMerchantCode());
		attributes.put("bankInfo", getBankInfo());
		attributes.put("paymentStatus", getPaymentStatus());
		attributes.put("paymentMethod", getPaymentMethod());
		attributes.put("confirmDatetime", getConfirmDatetime());
		attributes.put("confirmFileEntryId", getConfirmFileEntryId());
		attributes.put("confirmNote", getConfirmNote());
		attributes.put("approveDatetime", getApproveDatetime());
		attributes.put("accountUserName", getAccountUserName());
		attributes.put("govAgencyTaxNo", getGovAgencyTaxNo());
		attributes.put("invoiceTemplateNo", getInvoiceTemplateNo());
		attributes.put("invoiceIssueNo", getInvoiceIssueNo());
		attributes.put("invoiceNo", getInvoiceNo());
		attributes.put("invoiceFileEntryId", getInvoiceFileEntryId());
		attributes.put("paymentGateStatusCode", getPaymentGateStatusCode());
		attributes.put("paymentGateCheckCode", getPaymentGateCheckCode());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long paymentFileId = (Long)attributes.get("paymentFileId");

		if (paymentFileId != null) {
			setPaymentFileId(paymentFileId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String userName = (String)attributes.get("userName");

		if (userName != null) {
			setUserName(userName);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Long dossierId = (Long)attributes.get("dossierId");

		if (dossierId != null) {
			setDossierId(dossierId);
		}

		String referenceUid = (String)attributes.get("referenceUid");

		if (referenceUid != null) {
			setReferenceUid(referenceUid);
		}

		String govAgencyCode = (String)attributes.get("govAgencyCode");

		if (govAgencyCode != null) {
			setGovAgencyCode(govAgencyCode);
		}

		String govAgencyName = (String)attributes.get("govAgencyName");

		if (govAgencyName != null) {
			setGovAgencyName(govAgencyName);
		}

		Boolean isNew = (Boolean)attributes.get("isNew");

		if (isNew != null) {
			setIsNew(isNew);
		}

		String paymentFee = (String)attributes.get("paymentFee");

		if (paymentFee != null) {
			setPaymentFee(paymentFee);
		}

		Double paymentAmount = (Double)attributes.get("paymentAmount");

		if (paymentAmount != null) {
			setPaymentAmount(paymentAmount);
		}

		String paymentNote = (String)attributes.get("paymentNote");

		if (paymentNote != null) {
			setPaymentNote(paymentNote);
		}

		String paymentOptions = (String)attributes.get("paymentOptions");

		if (paymentOptions != null) {
			setPaymentOptions(paymentOptions);
		}

		Date keypayUrl = (Date)attributes.get("keypayUrl");

		if (keypayUrl != null) {
			setKeypayUrl(keypayUrl);
		}

		Long keypayTransactionId = (Long)attributes.get("keypayTransactionId");

		if (keypayTransactionId != null) {
			setKeypayTransactionId(keypayTransactionId);
		}

		Date keypayGoodCode = (Date)attributes.get("keypayGoodCode");

		if (keypayGoodCode != null) {
			setKeypayGoodCode(keypayGoodCode);
		}

		Date keypayMerchantCode = (Date)attributes.get("keypayMerchantCode");

		if (keypayMerchantCode != null) {
			setKeypayMerchantCode(keypayMerchantCode);
		}

		Date bankInfo = (Date)attributes.get("bankInfo");

		if (bankInfo != null) {
			setBankInfo(bankInfo);
		}

		Integer paymentStatus = (Integer)attributes.get("paymentStatus");

		if (paymentStatus != null) {
			setPaymentStatus(paymentStatus);
		}

		Integer paymentMethod = (Integer)attributes.get("paymentMethod");

		if (paymentMethod != null) {
			setPaymentMethod(paymentMethod);
		}

		Date confirmDatetime = (Date)attributes.get("confirmDatetime");

		if (confirmDatetime != null) {
			setConfirmDatetime(confirmDatetime);
		}

		Long confirmFileEntryId = (Long)attributes.get("confirmFileEntryId");

		if (confirmFileEntryId != null) {
			setConfirmFileEntryId(confirmFileEntryId);
		}

		String confirmNote = (String)attributes.get("confirmNote");

		if (confirmNote != null) {
			setConfirmNote(confirmNote);
		}

		Date approveDatetime = (Date)attributes.get("approveDatetime");

		if (approveDatetime != null) {
			setApproveDatetime(approveDatetime);
		}

		String accountUserName = (String)attributes.get("accountUserName");

		if (accountUserName != null) {
			setAccountUserName(accountUserName);
		}

		String govAgencyTaxNo = (String)attributes.get("govAgencyTaxNo");

		if (govAgencyTaxNo != null) {
			setGovAgencyTaxNo(govAgencyTaxNo);
		}

		String invoiceTemplateNo = (String)attributes.get("invoiceTemplateNo");

		if (invoiceTemplateNo != null) {
			setInvoiceTemplateNo(invoiceTemplateNo);
		}

		String invoiceIssueNo = (String)attributes.get("invoiceIssueNo");

		if (invoiceIssueNo != null) {
			setInvoiceIssueNo(invoiceIssueNo);
		}

		String invoiceNo = (String)attributes.get("invoiceNo");

		if (invoiceNo != null) {
			setInvoiceNo(invoiceNo);
		}

		Long invoiceFileEntryId = (Long)attributes.get("invoiceFileEntryId");

		if (invoiceFileEntryId != null) {
			setInvoiceFileEntryId(invoiceFileEntryId);
		}

		Integer paymentGateStatusCode = (Integer)attributes.get(
				"paymentGateStatusCode");

		if (paymentGateStatusCode != null) {
			setPaymentGateStatusCode(paymentGateStatusCode);
		}

		Integer paymentGateCheckCode = (Integer)attributes.get(
				"paymentGateCheckCode");

		if (paymentGateCheckCode != null) {
			setPaymentGateCheckCode(paymentGateCheckCode);
		}
	}

	@Override
	public PaymentFile toEscapedModel() {
		return new PaymentFileWrapper(_paymentFile.toEscapedModel());
	}

	@Override
	public PaymentFile toUnescapedModel() {
		return new PaymentFileWrapper(_paymentFile.toUnescapedModel());
	}

	/**
	* Returns the is new of this payment file.
	*
	* @return the is new of this payment file
	*/
	@Override
	public boolean getIsNew() {
		return _paymentFile.getIsNew();
	}

	@Override
	public boolean isCachedModel() {
		return _paymentFile.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _paymentFile.isEscapedModel();
	}

	/**
	* Returns <code>true</code> if this payment file is is new.
	*
	* @return <code>true</code> if this payment file is is new; <code>false</code> otherwise
	*/
	@Override
	public boolean isIsNew() {
		return _paymentFile.isIsNew();
	}

	@Override
	public boolean isNew() {
		return _paymentFile.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _paymentFile.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<PaymentFile> toCacheModel() {
		return _paymentFile.toCacheModel();
	}

	/**
	* Returns the payment amount of this payment file.
	*
	* @return the payment amount of this payment file
	*/
	@Override
	public double getPaymentAmount() {
		return _paymentFile.getPaymentAmount();
	}

	@Override
	public int compareTo(PaymentFile paymentFile) {
		return _paymentFile.compareTo(paymentFile);
	}

	/**
	* Returns the payment gate check code of this payment file.
	*
	* @return the payment gate check code of this payment file
	*/
	@Override
	public int getPaymentGateCheckCode() {
		return _paymentFile.getPaymentGateCheckCode();
	}

	/**
	* Returns the payment gate status code of this payment file.
	*
	* @return the payment gate status code of this payment file
	*/
	@Override
	public int getPaymentGateStatusCode() {
		return _paymentFile.getPaymentGateStatusCode();
	}

	/**
	* Returns the payment method of this payment file.
	*
	* @return the payment method of this payment file
	*/
	@Override
	public int getPaymentMethod() {
		return _paymentFile.getPaymentMethod();
	}

	/**
	* Returns the payment status of this payment file.
	*
	* @return the payment status of this payment file
	*/
	@Override
	public int getPaymentStatus() {
		return _paymentFile.getPaymentStatus();
	}

	@Override
	public int hashCode() {
		return _paymentFile.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _paymentFile.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new PaymentFileWrapper((PaymentFile)_paymentFile.clone());
	}

	/**
	* Returns the account user name of this payment file.
	*
	* @return the account user name of this payment file
	*/
	@Override
	public java.lang.String getAccountUserName() {
		return _paymentFile.getAccountUserName();
	}

	/**
	* Returns the confirm note of this payment file.
	*
	* @return the confirm note of this payment file
	*/
	@Override
	public java.lang.String getConfirmNote() {
		return _paymentFile.getConfirmNote();
	}

	/**
	* Returns the gov agency code of this payment file.
	*
	* @return the gov agency code of this payment file
	*/
	@Override
	public java.lang.String getGovAgencyCode() {
		return _paymentFile.getGovAgencyCode();
	}

	/**
	* Returns the gov agency name of this payment file.
	*
	* @return the gov agency name of this payment file
	*/
	@Override
	public java.lang.String getGovAgencyName() {
		return _paymentFile.getGovAgencyName();
	}

	/**
	* Returns the gov agency tax no of this payment file.
	*
	* @return the gov agency tax no of this payment file
	*/
	@Override
	public java.lang.String getGovAgencyTaxNo() {
		return _paymentFile.getGovAgencyTaxNo();
	}

	/**
	* Returns the invoice issue no of this payment file.
	*
	* @return the invoice issue no of this payment file
	*/
	@Override
	public java.lang.String getInvoiceIssueNo() {
		return _paymentFile.getInvoiceIssueNo();
	}

	/**
	* Returns the invoice no of this payment file.
	*
	* @return the invoice no of this payment file
	*/
	@Override
	public java.lang.String getInvoiceNo() {
		return _paymentFile.getInvoiceNo();
	}

	/**
	* Returns the invoice template no of this payment file.
	*
	* @return the invoice template no of this payment file
	*/
	@Override
	public java.lang.String getInvoiceTemplateNo() {
		return _paymentFile.getInvoiceTemplateNo();
	}

	/**
	* Returns the payment fee of this payment file.
	*
	* @return the payment fee of this payment file
	*/
	@Override
	public java.lang.String getPaymentFee() {
		return _paymentFile.getPaymentFee();
	}

	/**
	* Returns the payment note of this payment file.
	*
	* @return the payment note of this payment file
	*/
	@Override
	public java.lang.String getPaymentNote() {
		return _paymentFile.getPaymentNote();
	}

	/**
	* Returns the payment options of this payment file.
	*
	* @return the payment options of this payment file
	*/
	@Override
	public java.lang.String getPaymentOptions() {
		return _paymentFile.getPaymentOptions();
	}

	/**
	* Returns the reference uid of this payment file.
	*
	* @return the reference uid of this payment file
	*/
	@Override
	public java.lang.String getReferenceUid() {
		return _paymentFile.getReferenceUid();
	}

	/**
	* Returns the user name of this payment file.
	*
	* @return the user name of this payment file
	*/
	@Override
	public java.lang.String getUserName() {
		return _paymentFile.getUserName();
	}

	/**
	* Returns the user uuid of this payment file.
	*
	* @return the user uuid of this payment file
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _paymentFile.getUserUuid();
	}

	/**
	* Returns the uuid of this payment file.
	*
	* @return the uuid of this payment file
	*/
	@Override
	public java.lang.String getUuid() {
		return _paymentFile.getUuid();
	}

	@Override
	public java.lang.String toString() {
		return _paymentFile.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _paymentFile.toXmlString();
	}

	/**
	* Returns the approve datetime of this payment file.
	*
	* @return the approve datetime of this payment file
	*/
	@Override
	public Date getApproveDatetime() {
		return _paymentFile.getApproveDatetime();
	}

	/**
	* Returns the bank info of this payment file.
	*
	* @return the bank info of this payment file
	*/
	@Override
	public Date getBankInfo() {
		return _paymentFile.getBankInfo();
	}

	/**
	* Returns the confirm datetime of this payment file.
	*
	* @return the confirm datetime of this payment file
	*/
	@Override
	public Date getConfirmDatetime() {
		return _paymentFile.getConfirmDatetime();
	}

	/**
	* Returns the create date of this payment file.
	*
	* @return the create date of this payment file
	*/
	@Override
	public Date getCreateDate() {
		return _paymentFile.getCreateDate();
	}

	/**
	* Returns the keypay good code of this payment file.
	*
	* @return the keypay good code of this payment file
	*/
	@Override
	public Date getKeypayGoodCode() {
		return _paymentFile.getKeypayGoodCode();
	}

	/**
	* Returns the keypay merchant code of this payment file.
	*
	* @return the keypay merchant code of this payment file
	*/
	@Override
	public Date getKeypayMerchantCode() {
		return _paymentFile.getKeypayMerchantCode();
	}

	/**
	* Returns the keypay url of this payment file.
	*
	* @return the keypay url of this payment file
	*/
	@Override
	public Date getKeypayUrl() {
		return _paymentFile.getKeypayUrl();
	}

	/**
	* Returns the modified date of this payment file.
	*
	* @return the modified date of this payment file
	*/
	@Override
	public Date getModifiedDate() {
		return _paymentFile.getModifiedDate();
	}

	/**
	* Returns the company ID of this payment file.
	*
	* @return the company ID of this payment file
	*/
	@Override
	public long getCompanyId() {
		return _paymentFile.getCompanyId();
	}

	/**
	* Returns the confirm file entry ID of this payment file.
	*
	* @return the confirm file entry ID of this payment file
	*/
	@Override
	public long getConfirmFileEntryId() {
		return _paymentFile.getConfirmFileEntryId();
	}

	/**
	* Returns the dossier ID of this payment file.
	*
	* @return the dossier ID of this payment file
	*/
	@Override
	public long getDossierId() {
		return _paymentFile.getDossierId();
	}

	/**
	* Returns the group ID of this payment file.
	*
	* @return the group ID of this payment file
	*/
	@Override
	public long getGroupId() {
		return _paymentFile.getGroupId();
	}

	/**
	* Returns the invoice file entry ID of this payment file.
	*
	* @return the invoice file entry ID of this payment file
	*/
	@Override
	public long getInvoiceFileEntryId() {
		return _paymentFile.getInvoiceFileEntryId();
	}

	/**
	* Returns the keypay transaction ID of this payment file.
	*
	* @return the keypay transaction ID of this payment file
	*/
	@Override
	public long getKeypayTransactionId() {
		return _paymentFile.getKeypayTransactionId();
	}

	/**
	* Returns the payment file ID of this payment file.
	*
	* @return the payment file ID of this payment file
	*/
	@Override
	public long getPaymentFileId() {
		return _paymentFile.getPaymentFileId();
	}

	/**
	* Returns the primary key of this payment file.
	*
	* @return the primary key of this payment file
	*/
	@Override
	public long getPrimaryKey() {
		return _paymentFile.getPrimaryKey();
	}

	/**
	* Returns the user ID of this payment file.
	*
	* @return the user ID of this payment file
	*/
	@Override
	public long getUserId() {
		return _paymentFile.getUserId();
	}

	@Override
	public void persist() {
		_paymentFile.persist();
	}

	/**
	* Sets the account user name of this payment file.
	*
	* @param accountUserName the account user name of this payment file
	*/
	@Override
	public void setAccountUserName(java.lang.String accountUserName) {
		_paymentFile.setAccountUserName(accountUserName);
	}

	/**
	* Sets the approve datetime of this payment file.
	*
	* @param approveDatetime the approve datetime of this payment file
	*/
	@Override
	public void setApproveDatetime(Date approveDatetime) {
		_paymentFile.setApproveDatetime(approveDatetime);
	}

	/**
	* Sets the bank info of this payment file.
	*
	* @param bankInfo the bank info of this payment file
	*/
	@Override
	public void setBankInfo(Date bankInfo) {
		_paymentFile.setBankInfo(bankInfo);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_paymentFile.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this payment file.
	*
	* @param companyId the company ID of this payment file
	*/
	@Override
	public void setCompanyId(long companyId) {
		_paymentFile.setCompanyId(companyId);
	}

	/**
	* Sets the confirm datetime of this payment file.
	*
	* @param confirmDatetime the confirm datetime of this payment file
	*/
	@Override
	public void setConfirmDatetime(Date confirmDatetime) {
		_paymentFile.setConfirmDatetime(confirmDatetime);
	}

	/**
	* Sets the confirm file entry ID of this payment file.
	*
	* @param confirmFileEntryId the confirm file entry ID of this payment file
	*/
	@Override
	public void setConfirmFileEntryId(long confirmFileEntryId) {
		_paymentFile.setConfirmFileEntryId(confirmFileEntryId);
	}

	/**
	* Sets the confirm note of this payment file.
	*
	* @param confirmNote the confirm note of this payment file
	*/
	@Override
	public void setConfirmNote(java.lang.String confirmNote) {
		_paymentFile.setConfirmNote(confirmNote);
	}

	/**
	* Sets the create date of this payment file.
	*
	* @param createDate the create date of this payment file
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_paymentFile.setCreateDate(createDate);
	}

	/**
	* Sets the dossier ID of this payment file.
	*
	* @param dossierId the dossier ID of this payment file
	*/
	@Override
	public void setDossierId(long dossierId) {
		_paymentFile.setDossierId(dossierId);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_paymentFile.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_paymentFile.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_paymentFile.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the gov agency code of this payment file.
	*
	* @param govAgencyCode the gov agency code of this payment file
	*/
	@Override
	public void setGovAgencyCode(java.lang.String govAgencyCode) {
		_paymentFile.setGovAgencyCode(govAgencyCode);
	}

	/**
	* Sets the gov agency name of this payment file.
	*
	* @param govAgencyName the gov agency name of this payment file
	*/
	@Override
	public void setGovAgencyName(java.lang.String govAgencyName) {
		_paymentFile.setGovAgencyName(govAgencyName);
	}

	/**
	* Sets the gov agency tax no of this payment file.
	*
	* @param govAgencyTaxNo the gov agency tax no of this payment file
	*/
	@Override
	public void setGovAgencyTaxNo(java.lang.String govAgencyTaxNo) {
		_paymentFile.setGovAgencyTaxNo(govAgencyTaxNo);
	}

	/**
	* Sets the group ID of this payment file.
	*
	* @param groupId the group ID of this payment file
	*/
	@Override
	public void setGroupId(long groupId) {
		_paymentFile.setGroupId(groupId);
	}

	/**
	* Sets the invoice file entry ID of this payment file.
	*
	* @param invoiceFileEntryId the invoice file entry ID of this payment file
	*/
	@Override
	public void setInvoiceFileEntryId(long invoiceFileEntryId) {
		_paymentFile.setInvoiceFileEntryId(invoiceFileEntryId);
	}

	/**
	* Sets the invoice issue no of this payment file.
	*
	* @param invoiceIssueNo the invoice issue no of this payment file
	*/
	@Override
	public void setInvoiceIssueNo(java.lang.String invoiceIssueNo) {
		_paymentFile.setInvoiceIssueNo(invoiceIssueNo);
	}

	/**
	* Sets the invoice no of this payment file.
	*
	* @param invoiceNo the invoice no of this payment file
	*/
	@Override
	public void setInvoiceNo(java.lang.String invoiceNo) {
		_paymentFile.setInvoiceNo(invoiceNo);
	}

	/**
	* Sets the invoice template no of this payment file.
	*
	* @param invoiceTemplateNo the invoice template no of this payment file
	*/
	@Override
	public void setInvoiceTemplateNo(java.lang.String invoiceTemplateNo) {
		_paymentFile.setInvoiceTemplateNo(invoiceTemplateNo);
	}

	/**
	* Sets whether this payment file is is new.
	*
	* @param isNew the is new of this payment file
	*/
	@Override
	public void setIsNew(boolean isNew) {
		_paymentFile.setIsNew(isNew);
	}

	/**
	* Sets the keypay good code of this payment file.
	*
	* @param keypayGoodCode the keypay good code of this payment file
	*/
	@Override
	public void setKeypayGoodCode(Date keypayGoodCode) {
		_paymentFile.setKeypayGoodCode(keypayGoodCode);
	}

	/**
	* Sets the keypay merchant code of this payment file.
	*
	* @param keypayMerchantCode the keypay merchant code of this payment file
	*/
	@Override
	public void setKeypayMerchantCode(Date keypayMerchantCode) {
		_paymentFile.setKeypayMerchantCode(keypayMerchantCode);
	}

	/**
	* Sets the keypay transaction ID of this payment file.
	*
	* @param keypayTransactionId the keypay transaction ID of this payment file
	*/
	@Override
	public void setKeypayTransactionId(long keypayTransactionId) {
		_paymentFile.setKeypayTransactionId(keypayTransactionId);
	}

	/**
	* Sets the keypay url of this payment file.
	*
	* @param keypayUrl the keypay url of this payment file
	*/
	@Override
	public void setKeypayUrl(Date keypayUrl) {
		_paymentFile.setKeypayUrl(keypayUrl);
	}

	/**
	* Sets the modified date of this payment file.
	*
	* @param modifiedDate the modified date of this payment file
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_paymentFile.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_paymentFile.setNew(n);
	}

	/**
	* Sets the payment amount of this payment file.
	*
	* @param paymentAmount the payment amount of this payment file
	*/
	@Override
	public void setPaymentAmount(double paymentAmount) {
		_paymentFile.setPaymentAmount(paymentAmount);
	}

	/**
	* Sets the payment fee of this payment file.
	*
	* @param paymentFee the payment fee of this payment file
	*/
	@Override
	public void setPaymentFee(java.lang.String paymentFee) {
		_paymentFile.setPaymentFee(paymentFee);
	}

	/**
	* Sets the payment file ID of this payment file.
	*
	* @param paymentFileId the payment file ID of this payment file
	*/
	@Override
	public void setPaymentFileId(long paymentFileId) {
		_paymentFile.setPaymentFileId(paymentFileId);
	}

	/**
	* Sets the payment gate check code of this payment file.
	*
	* @param paymentGateCheckCode the payment gate check code of this payment file
	*/
	@Override
	public void setPaymentGateCheckCode(int paymentGateCheckCode) {
		_paymentFile.setPaymentGateCheckCode(paymentGateCheckCode);
	}

	/**
	* Sets the payment gate status code of this payment file.
	*
	* @param paymentGateStatusCode the payment gate status code of this payment file
	*/
	@Override
	public void setPaymentGateStatusCode(int paymentGateStatusCode) {
		_paymentFile.setPaymentGateStatusCode(paymentGateStatusCode);
	}

	/**
	* Sets the payment method of this payment file.
	*
	* @param paymentMethod the payment method of this payment file
	*/
	@Override
	public void setPaymentMethod(int paymentMethod) {
		_paymentFile.setPaymentMethod(paymentMethod);
	}

	/**
	* Sets the payment note of this payment file.
	*
	* @param paymentNote the payment note of this payment file
	*/
	@Override
	public void setPaymentNote(java.lang.String paymentNote) {
		_paymentFile.setPaymentNote(paymentNote);
	}

	/**
	* Sets the payment options of this payment file.
	*
	* @param paymentOptions the payment options of this payment file
	*/
	@Override
	public void setPaymentOptions(java.lang.String paymentOptions) {
		_paymentFile.setPaymentOptions(paymentOptions);
	}

	/**
	* Sets the payment status of this payment file.
	*
	* @param paymentStatus the payment status of this payment file
	*/
	@Override
	public void setPaymentStatus(int paymentStatus) {
		_paymentFile.setPaymentStatus(paymentStatus);
	}

	/**
	* Sets the primary key of this payment file.
	*
	* @param primaryKey the primary key of this payment file
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_paymentFile.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_paymentFile.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the reference uid of this payment file.
	*
	* @param referenceUid the reference uid of this payment file
	*/
	@Override
	public void setReferenceUid(java.lang.String referenceUid) {
		_paymentFile.setReferenceUid(referenceUid);
	}

	/**
	* Sets the user ID of this payment file.
	*
	* @param userId the user ID of this payment file
	*/
	@Override
	public void setUserId(long userId) {
		_paymentFile.setUserId(userId);
	}

	/**
	* Sets the user name of this payment file.
	*
	* @param userName the user name of this payment file
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_paymentFile.setUserName(userName);
	}

	/**
	* Sets the user uuid of this payment file.
	*
	* @param userUuid the user uuid of this payment file
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_paymentFile.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this payment file.
	*
	* @param uuid the uuid of this payment file
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_paymentFile.setUuid(uuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof PaymentFileWrapper)) {
			return false;
		}

		PaymentFileWrapper paymentFileWrapper = (PaymentFileWrapper)obj;

		if (Objects.equals(_paymentFile, paymentFileWrapper._paymentFile)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _paymentFile.getStagedModelType();
	}

	@Override
	public PaymentFile getWrappedModel() {
		return _paymentFile;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _paymentFile.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _paymentFile.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_paymentFile.resetOriginalValues();
	}

	private final PaymentFile _paymentFile;
}