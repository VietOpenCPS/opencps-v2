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

package org.opencps.dossiermgt.model;

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
		attributes.put("paymentFee", getPaymentFee());
		attributes.put("advanceAmount", getAdvanceAmount());
		attributes.put("feeAmount", getFeeAmount());
		attributes.put("serviceAmount", getServiceAmount());
		attributes.put("shipAmount", getShipAmount());
		attributes.put("paymentAmount", getPaymentAmount());
		attributes.put("paymentNote", getPaymentNote());
		attributes.put("epaymentProfile", getEpaymentProfile());
		attributes.put("bankInfo", getBankInfo());
		attributes.put("paymentStatus", getPaymentStatus());
		attributes.put("paymentMethod", getPaymentMethod());
		attributes.put("confirmDatetime", getConfirmDatetime());
		attributes.put("confirmPayload", getConfirmPayload());
		attributes.put("confirmFileEntryId", getConfirmFileEntryId());
		attributes.put("confirmNote", getConfirmNote());
		attributes.put("approveDatetime", getApproveDatetime());
		attributes.put("accountUserName", getAccountUserName());
		attributes.put("govAgencyTaxNo", getGovAgencyTaxNo());
		attributes.put("invoiceTemplateNo", getInvoiceTemplateNo());
		attributes.put("invoiceIssueNo", getInvoiceIssueNo());
		attributes.put("invoiceNo", getInvoiceNo());
		attributes.put("invoicePayload", getInvoicePayload());
		attributes.put("einvoice", getEinvoice());

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

		String paymentFee = (String)attributes.get("paymentFee");

		if (paymentFee != null) {
			setPaymentFee(paymentFee);
		}

		Long advanceAmount = (Long)attributes.get("advanceAmount");

		if (advanceAmount != null) {
			setAdvanceAmount(advanceAmount);
		}

		Long feeAmount = (Long)attributes.get("feeAmount");

		if (feeAmount != null) {
			setFeeAmount(feeAmount);
		}

		Long serviceAmount = (Long)attributes.get("serviceAmount");

		if (serviceAmount != null) {
			setServiceAmount(serviceAmount);
		}

		Long shipAmount = (Long)attributes.get("shipAmount");

		if (shipAmount != null) {
			setShipAmount(shipAmount);
		}

		Long paymentAmount = (Long)attributes.get("paymentAmount");

		if (paymentAmount != null) {
			setPaymentAmount(paymentAmount);
		}

		String paymentNote = (String)attributes.get("paymentNote");

		if (paymentNote != null) {
			setPaymentNote(paymentNote);
		}

		String epaymentProfile = (String)attributes.get("epaymentProfile");

		if (epaymentProfile != null) {
			setEpaymentProfile(epaymentProfile);
		}

		String bankInfo = (String)attributes.get("bankInfo");

		if (bankInfo != null) {
			setBankInfo(bankInfo);
		}

		Integer paymentStatus = (Integer)attributes.get("paymentStatus");

		if (paymentStatus != null) {
			setPaymentStatus(paymentStatus);
		}

		String paymentMethod = (String)attributes.get("paymentMethod");

		if (paymentMethod != null) {
			setPaymentMethod(paymentMethod);
		}

		Date confirmDatetime = (Date)attributes.get("confirmDatetime");

		if (confirmDatetime != null) {
			setConfirmDatetime(confirmDatetime);
		}

		String confirmPayload = (String)attributes.get("confirmPayload");

		if (confirmPayload != null) {
			setConfirmPayload(confirmPayload);
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

		String invoicePayload = (String)attributes.get("invoicePayload");

		if (invoicePayload != null) {
			setInvoicePayload(invoicePayload);
		}

		String einvoice = (String)attributes.get("einvoice");

		if (einvoice != null) {
			setEinvoice(einvoice);
		}
	}

	@Override
	public Object clone() {
		return new PaymentFileWrapper((PaymentFile)_paymentFile.clone());
	}

	@Override
	public int compareTo(PaymentFile paymentFile) {
		return _paymentFile.compareTo(paymentFile);
	}

	/**
	* Returns the account user name of this payment file.
	*
	* @return the account user name of this payment file
	*/
	@Override
	public String getAccountUserName() {
		return _paymentFile.getAccountUserName();
	}

	/**
	* Returns the advance amount of this payment file.
	*
	* @return the advance amount of this payment file
	*/
	@Override
	public long getAdvanceAmount() {
		return _paymentFile.getAdvanceAmount();
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
	public String getBankInfo() {
		return _paymentFile.getBankInfo();
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
	* Returns the confirm datetime of this payment file.
	*
	* @return the confirm datetime of this payment file
	*/
	@Override
	public Date getConfirmDatetime() {
		return _paymentFile.getConfirmDatetime();
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
	* Returns the confirm note of this payment file.
	*
	* @return the confirm note of this payment file
	*/
	@Override
	public String getConfirmNote() {
		return _paymentFile.getConfirmNote();
	}

	/**
	* Returns the confirm payload of this payment file.
	*
	* @return the confirm payload of this payment file
	*/
	@Override
	public String getConfirmPayload() {
		return _paymentFile.getConfirmPayload();
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
	* Returns the dossier ID of this payment file.
	*
	* @return the dossier ID of this payment file
	*/
	@Override
	public long getDossierId() {
		return _paymentFile.getDossierId();
	}

	/**
	* Returns the einvoice of this payment file.
	*
	* @return the einvoice of this payment file
	*/
	@Override
	public String getEinvoice() {
		return _paymentFile.getEinvoice();
	}

	/**
	* Returns the epayment profile of this payment file.
	*
	* @return the epayment profile of this payment file
	*/
	@Override
	public String getEpaymentProfile() {
		return _paymentFile.getEpaymentProfile();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _paymentFile.getExpandoBridge();
	}

	/**
	* Returns the fee amount of this payment file.
	*
	* @return the fee amount of this payment file
	*/
	@Override
	public long getFeeAmount() {
		return _paymentFile.getFeeAmount();
	}

	/**
	* Returns the gov agency tax no of this payment file.
	*
	* @return the gov agency tax no of this payment file
	*/
	@Override
	public String getGovAgencyTaxNo() {
		return _paymentFile.getGovAgencyTaxNo();
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
	* Returns the invoice issue no of this payment file.
	*
	* @return the invoice issue no of this payment file
	*/
	@Override
	public String getInvoiceIssueNo() {
		return _paymentFile.getInvoiceIssueNo();
	}

	/**
	* Returns the invoice no of this payment file.
	*
	* @return the invoice no of this payment file
	*/
	@Override
	public String getInvoiceNo() {
		return _paymentFile.getInvoiceNo();
	}

	/**
	* Returns the invoice payload of this payment file.
	*
	* @return the invoice payload of this payment file
	*/
	@Override
	public String getInvoicePayload() {
		return _paymentFile.getInvoicePayload();
	}

	/**
	* Returns the invoice template no of this payment file.
	*
	* @return the invoice template no of this payment file
	*/
	@Override
	public String getInvoiceTemplateNo() {
		return _paymentFile.getInvoiceTemplateNo();
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
	* Returns the payment amount of this payment file.
	*
	* @return the payment amount of this payment file
	*/
	@Override
	public long getPaymentAmount() {
		return _paymentFile.getPaymentAmount();
	}

	/**
	* Returns the payment fee of this payment file.
	*
	* @return the payment fee of this payment file
	*/
	@Override
	public String getPaymentFee() {
		return _paymentFile.getPaymentFee();
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
	* Returns the payment method of this payment file.
	*
	* @return the payment method of this payment file
	*/
	@Override
	public String getPaymentMethod() {
		return _paymentFile.getPaymentMethod();
	}

	/**
	* Returns the payment note of this payment file.
	*
	* @return the payment note of this payment file
	*/
	@Override
	public String getPaymentNote() {
		return _paymentFile.getPaymentNote();
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

	/**
	* Returns the primary key of this payment file.
	*
	* @return the primary key of this payment file
	*/
	@Override
	public long getPrimaryKey() {
		return _paymentFile.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _paymentFile.getPrimaryKeyObj();
	}

	/**
	* Returns the reference uid of this payment file.
	*
	* @return the reference uid of this payment file
	*/
	@Override
	public String getReferenceUid() {
		return _paymentFile.getReferenceUid();
	}

	/**
	* Returns the service amount of this payment file.
	*
	* @return the service amount of this payment file
	*/
	@Override
	public long getServiceAmount() {
		return _paymentFile.getServiceAmount();
	}

	/**
	* Returns the ship amount of this payment file.
	*
	* @return the ship amount of this payment file
	*/
	@Override
	public long getShipAmount() {
		return _paymentFile.getShipAmount();
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

	/**
	* Returns the user name of this payment file.
	*
	* @return the user name of this payment file
	*/
	@Override
	public String getUserName() {
		return _paymentFile.getUserName();
	}

	/**
	* Returns the user uuid of this payment file.
	*
	* @return the user uuid of this payment file
	*/
	@Override
	public String getUserUuid() {
		return _paymentFile.getUserUuid();
	}

	/**
	* Returns the uuid of this payment file.
	*
	* @return the uuid of this payment file
	*/
	@Override
	public String getUuid() {
		return _paymentFile.getUuid();
	}

	@Override
	public int hashCode() {
		return _paymentFile.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _paymentFile.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _paymentFile.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _paymentFile.isNew();
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
	public void setAccountUserName(String accountUserName) {
		_paymentFile.setAccountUserName(accountUserName);
	}

	/**
	* Sets the advance amount of this payment file.
	*
	* @param advanceAmount the advance amount of this payment file
	*/
	@Override
	public void setAdvanceAmount(long advanceAmount) {
		_paymentFile.setAdvanceAmount(advanceAmount);
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
	public void setBankInfo(String bankInfo) {
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
	public void setConfirmNote(String confirmNote) {
		_paymentFile.setConfirmNote(confirmNote);
	}

	/**
	* Sets the confirm payload of this payment file.
	*
	* @param confirmPayload the confirm payload of this payment file
	*/
	@Override
	public void setConfirmPayload(String confirmPayload) {
		_paymentFile.setConfirmPayload(confirmPayload);
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

	/**
	* Sets the einvoice of this payment file.
	*
	* @param einvoice the einvoice of this payment file
	*/
	@Override
	public void setEinvoice(String einvoice) {
		_paymentFile.setEinvoice(einvoice);
	}

	/**
	* Sets the epayment profile of this payment file.
	*
	* @param epaymentProfile the epayment profile of this payment file
	*/
	@Override
	public void setEpaymentProfile(String epaymentProfile) {
		_paymentFile.setEpaymentProfile(epaymentProfile);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_paymentFile.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_paymentFile.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_paymentFile.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the fee amount of this payment file.
	*
	* @param feeAmount the fee amount of this payment file
	*/
	@Override
	public void setFeeAmount(long feeAmount) {
		_paymentFile.setFeeAmount(feeAmount);
	}

	/**
	* Sets the gov agency tax no of this payment file.
	*
	* @param govAgencyTaxNo the gov agency tax no of this payment file
	*/
	@Override
	public void setGovAgencyTaxNo(String govAgencyTaxNo) {
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
	* Sets the invoice issue no of this payment file.
	*
	* @param invoiceIssueNo the invoice issue no of this payment file
	*/
	@Override
	public void setInvoiceIssueNo(String invoiceIssueNo) {
		_paymentFile.setInvoiceIssueNo(invoiceIssueNo);
	}

	/**
	* Sets the invoice no of this payment file.
	*
	* @param invoiceNo the invoice no of this payment file
	*/
	@Override
	public void setInvoiceNo(String invoiceNo) {
		_paymentFile.setInvoiceNo(invoiceNo);
	}

	/**
	* Sets the invoice payload of this payment file.
	*
	* @param invoicePayload the invoice payload of this payment file
	*/
	@Override
	public void setInvoicePayload(String invoicePayload) {
		_paymentFile.setInvoicePayload(invoicePayload);
	}

	/**
	* Sets the invoice template no of this payment file.
	*
	* @param invoiceTemplateNo the invoice template no of this payment file
	*/
	@Override
	public void setInvoiceTemplateNo(String invoiceTemplateNo) {
		_paymentFile.setInvoiceTemplateNo(invoiceTemplateNo);
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
	public void setPaymentAmount(long paymentAmount) {
		_paymentFile.setPaymentAmount(paymentAmount);
	}

	/**
	* Sets the payment fee of this payment file.
	*
	* @param paymentFee the payment fee of this payment file
	*/
	@Override
	public void setPaymentFee(String paymentFee) {
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
	* Sets the payment method of this payment file.
	*
	* @param paymentMethod the payment method of this payment file
	*/
	@Override
	public void setPaymentMethod(String paymentMethod) {
		_paymentFile.setPaymentMethod(paymentMethod);
	}

	/**
	* Sets the payment note of this payment file.
	*
	* @param paymentNote the payment note of this payment file
	*/
	@Override
	public void setPaymentNote(String paymentNote) {
		_paymentFile.setPaymentNote(paymentNote);
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
	public void setReferenceUid(String referenceUid) {
		_paymentFile.setReferenceUid(referenceUid);
	}

	/**
	* Sets the service amount of this payment file.
	*
	* @param serviceAmount the service amount of this payment file
	*/
	@Override
	public void setServiceAmount(long serviceAmount) {
		_paymentFile.setServiceAmount(serviceAmount);
	}

	/**
	* Sets the ship amount of this payment file.
	*
	* @param shipAmount the ship amount of this payment file
	*/
	@Override
	public void setShipAmount(long shipAmount) {
		_paymentFile.setShipAmount(shipAmount);
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
	public void setUserName(String userName) {
		_paymentFile.setUserName(userName);
	}

	/**
	* Sets the user uuid of this payment file.
	*
	* @param userUuid the user uuid of this payment file
	*/
	@Override
	public void setUserUuid(String userUuid) {
		_paymentFile.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this payment file.
	*
	* @param uuid the uuid of this payment file
	*/
	@Override
	public void setUuid(String uuid) {
		_paymentFile.setUuid(uuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<PaymentFile> toCacheModel() {
		return _paymentFile.toCacheModel();
	}

	@Override
	public PaymentFile toEscapedModel() {
		return new PaymentFileWrapper(_paymentFile.toEscapedModel());
	}

	@Override
	public String toString() {
		return _paymentFile.toString();
	}

	@Override
	public PaymentFile toUnescapedModel() {
		return new PaymentFileWrapper(_paymentFile.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _paymentFile.toXmlString();
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